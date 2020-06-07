package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.entity.Movie;
import com.trainingProject.moviesWebApp.enums.SortingOrder;
import com.trainingProject.moviesWebApp.enums.SortingType;
import com.trainingProject.moviesWebApp.exceptions.NotExistingMovieException;
import com.trainingProject.moviesWebApp.mapper.MovieMapper;
import com.trainingProject.moviesWebApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
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
        return repository.findAll()
                .stream()
                .map(x -> mapper.toMovieDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> findAllMoviesAndSort(SortingOrder sortingOrder, SortingType sortingType) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortingOrder.getOrder()), sortingType.getField());
        return repository.findAll(sort)
                .stream()
                .map(x -> mapper.toMovieDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie = Optional.ofNullable(repository.findMovieByMovieId(id))
                .orElseThrow(() -> new NotExistingMovieException("This movie does not exists!"));
        return mapper.toMovieDto(movie);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        Movie movie = repository.save(mapper.toMovie(movieDto));
        return mapper.toMovieDto(movie);
    }

    @Override
    public List<MovieDto> findAndSortMoviesByUser_Id(Long id, SortingOrder sortingOrder, SortingType sortingType) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortingOrder.getOrder()), sortingType.getField());
        return repository.findMoviesByUser_Id(id, sort)
                .stream()
                .map(x -> mapper.toMovieDto(x))
                .collect(Collectors.toList());

    }

    @Override
    public boolean deleteMovie(Long id) {
        Movie movieToDelete = Optional.ofNullable(repository.findMovieByMovieId(id))
                .orElseThrow(() -> new NotExistingMovieException("This movie does not exists!"));
        repository.delete(movieToDelete);
        return true;
    }
}
