package com.trainingProject.moviesWebApp.mapper;

import com.trainingProject.moviesWebApp.dto.VoteDto;
import com.trainingProject.moviesWebApp.entity.UserMovieKey;
import com.trainingProject.moviesWebApp.entity.Vote;
import com.trainingProject.moviesWebApp.enums.Rate;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public Vote toVote(VoteDto voteDto) {
        Vote vote = new Vote();
        UserMovieKey userMovieKey = new UserMovieKey(voteDto.getUser_id(), voteDto.getMovie_id());
        vote.setId(userMovieKey);
        vote.setRate(Rate.valueOf(voteDto.getRate()));
        return vote;
    }
}

