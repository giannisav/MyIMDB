package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.entity.Movie;
import com.trainingProject.moviesWebApp.exceptions.NotExistingMovieException;
import com.trainingProject.moviesWebApp.mapper.MovieMapper;
import com.trainingProject.moviesWebApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;
    private MovieMapper mapper;

    @Autowired
    public MovieServiceImpl(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return repository.findAll().stream()
                         .map(x -> mapper.movieToMovieDto(x))
                         .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(long id) {
        return mapper.movieToMovieDto(repository.findMovieByMovieId(id));
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto) {
        Movie movieToUpdate = repository.findMovieByMovieId(movieDto.getId());
        movieToUpdate.setName(movieDto.getName());
        movieToUpdate.setDirectorName(movieDto.getDirectorsName());
        movieToUpdate.setYearOfPublication(movieDto.getYearOfPublication());

        return mapper.movieToMovieDto(repository.save(movieToUpdate));
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto) {
        Movie movieToSave = mapper.movieDtoToMovie(movieDto);
        return mapper.movieToMovieDto(repository.save(movieToSave));
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if(movieDto.getId() != null){ return updateMovie(movieDto);}
        else { return addMovie(movieDto); }
    }

    @Override
    public boolean deleteMovie(long id) {
        Movie movieToDelete = Optional.ofNullable(repository.findMovieByMovieId(id)).orElseThrow(NotExistingMovieException::new);
        repository.delete(movieToDelete);
        return true;
    }
}
