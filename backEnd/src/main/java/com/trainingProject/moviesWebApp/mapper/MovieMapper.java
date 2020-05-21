package com.trainingProject.moviesWebApp.mapper;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie movieDtoToMovie(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setName(movieDto.getName());
        movie.setDirectorName(movieDto.getDirectorsName());
        movie.setYearOfPublication(movieDto.getYearOfPublication());
        return movie;
    }

    public MovieDto movieToMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getMovieId());
        movieDto.setName(movie.getName());
        movieDto.setDirectorsName(movie.getDirectorName());
        movieDto.setYearOfPublication(movie.getYearOfPublication());
        return movieDto;
    }
}
