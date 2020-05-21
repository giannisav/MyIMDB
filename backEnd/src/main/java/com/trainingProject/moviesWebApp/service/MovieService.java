package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDto updateMovie(MovieDto movieDto);
    MovieDto addMovie(MovieDto movieDto);
    boolean deleteMovie(long id);
    MovieDto getMovieById(long id);
    MovieDto save(MovieDto movieDto);
}
