package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.entity.Movie;
import com.trainingProject.moviesWebApp.enums.SortingOrder;
import com.trainingProject.moviesWebApp.enums.SortingType;
import com.trainingProject.moviesWebApp.mapper.MovieMapper;
import com.trainingProject.moviesWebApp.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


public class MovieServiceImplTest {

    @Test
    public void test_findAll() {
        MovieRepository stubMovieRepository = mock(MovieRepository.class);
        MovieMapper movieMapper = mock(MovieMapper.class);

        List<Movie> movieList = new ArrayList<>();
        Movie firstMovie = new Movie();
        Movie secondMovie = new Movie();
        movieList.add(firstMovie);
        movieList.add(secondMovie);

        MovieDto firstMovieDto = new MovieDto();
        MovieDto secondMovieDto = new MovieDto();

        when(stubMovieRepository.findAll(any(Sort.class))).thenReturn(movieList);
        when(movieMapper.toMovieDto(firstMovie)).thenReturn(firstMovieDto);
        when(movieMapper.toMovieDto(secondMovie)).thenReturn(secondMovieDto);

        MovieServiceImpl movieService = new MovieServiceImpl(stubMovieRepository, movieMapper);
        List<MovieDto> movies = movieService.findAllMoviesAndSort(SortingOrder.DESC, SortingType.LIKES);
        Assertions.assertEquals(2, movies.size());

    }

    @Test
    public void test_findMoviesLast() {
        MovieRepository stubMovieRepository = mock(MovieRepository.class);
        MovieMapper movieMapper = mock(MovieMapper.class);

        List<Movie> movieList = new ArrayList<>();
        Movie firstMovie = new Movie();
        Movie secondMovie = new Movie();
        movieList.add(firstMovie);
        movieList.add(secondMovie);

        when(stubMovieRepository.findAll(any(Sort.class))).thenReturn(movieList);
        MovieDto movieDto = new MovieDto();
        when(movieMapper.toMovieDto(firstMovie)).thenReturn(movieDto);
        MovieServiceImpl movieService = new MovieServiceImpl(stubMovieRepository, movieMapper);
        List<MovieDto> movies = movieService.findAllMoviesAndSort(null, null);
        Assertions.assertEquals(2, movies.size());
    }
}
