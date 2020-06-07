package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.enums.SortingOrder;
import com.trainingProject.moviesWebApp.enums.SortingType;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    List<MovieDto> findAllMoviesAndSort(SortingOrder sortingOrder, SortingType sortingType);

    MovieDto findMovieById(Long id);

    List<MovieDto> findAndSortMoviesByUser_Id(Long id, SortingOrder sortingOrder, SortingType sortingType);

    MovieDto save(MovieDto movieDto);

    boolean delete(Long id);
}
