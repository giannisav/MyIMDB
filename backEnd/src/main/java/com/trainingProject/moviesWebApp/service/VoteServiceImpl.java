package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.dto.VoteDto;
import com.trainingProject.moviesWebApp.entity.Movie;
import com.trainingProject.moviesWebApp.entity.UserMovieKey;
import com.trainingProject.moviesWebApp.entity.Vote;
import com.trainingProject.moviesWebApp.enums.Rate;
import com.trainingProject.moviesWebApp.exceptions.NotExistingVoteException;
import com.trainingProject.moviesWebApp.exceptions.SpamVoteException;
import com.trainingProject.moviesWebApp.mapper.MovieMapper;
import com.trainingProject.moviesWebApp.mapper.VoteMapper;
import com.trainingProject.moviesWebApp.repository.MovieRepository;
import com.trainingProject.moviesWebApp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteMapper voteMapper;
    private VoteRepository voteRepository;
    private MovieRepository movieRepository;
    private MovieMapper movieMapper;

    @Autowired
    VoteServiceImpl(VoteMapper voteMapper, VoteRepository voteRepository, MovieRepository movieRepository, MovieMapper movieMapper) {
        this.voteMapper = voteMapper;
        this.voteRepository = voteRepository;
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    //TODO: break method and remove duplicates
    @Transactional
    @Override
    public MovieDto vote(VoteDto voteDto) {

        UserMovieKey key = new UserMovieKey(voteDto.getUser_id(), voteDto.getMovie_id());
        Optional<Vote> vote = voteRepository.findVoteById(key);
        boolean isUpvote = voteDto.getRate().equals(Rate.UPVOTE.name());

        if (vote.isEmpty()) {
            Vote savedVote = voteRepository.save(voteMapper.toVote(voteDto));
            Movie movie = movieRepository.findMovieByMovieId(savedVote.getId().getMovieId());
            if (isUpvote) {
                movie.setLikes(movie.getLikes() + 1L);
            } else {
                movie.setDislikes(movie.getDislikes() + 1L);
            }
            return movieMapper.toMovieDto(movieRepository.save(movie));
        } else if (vote.get().getRate().equals(Rate.valueOf(voteDto.getRate()))) {
            throw new SpamVoteException("You cant vote one movie more than 1 time");
        } else {
            vote.get().setRate(Rate.valueOf(voteDto.getRate()));
            Vote updatedVote = voteRepository.save(vote.get());
            Movie movie = movieRepository.findMovieByMovieId(updatedVote.getId().getMovieId());
            if (isUpvote) {
                movie.setDislikes(movie.getDislikes() - 1L);
                movie.setLikes(movie.getLikes() + 1L);
            } else {
                movie.setDislikes(movie.getDislikes() + 1L);
                movie.setLikes(movie.getLikes() - 1L);
            }
            return movieMapper.toMovieDto(movieRepository.save(movie));
        }
    }

    @Override
    public MovieDto deleteVote(VoteDto voteDto) {
        UserMovieKey userMovieKey = new UserMovieKey(voteDto.getUser_id(), voteDto.getMovie_id());
        Vote vote = voteRepository.findVoteById(userMovieKey).orElseThrow(() -> new NotExistingVoteException("Vote does not exists!"));

        Movie movie = movieRepository.findMovieByMovieId(voteDto.getMovie_id());
        if (voteDto.getRate().equals(Rate.DOWNVOTE.name())) {
            movie.setDislikes(movie.getDislikes() - 1L);
        } else {
            movie.setLikes(movie.getLikes() - 1L);
        }
        voteRepository.delete(vote);

        return movieMapper.toMovieDto(movie);
    }

}

