package com.trainingProject.moviesWebApp.mapper;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.entity.Movie;
import com.trainingProject.moviesWebApp.entity.User;
import com.trainingProject.moviesWebApp.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    private UserRepository userRepository;

    public MovieMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Movie toMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDirector(movieDto.getDirector());
        movie.setDescription(movieDto.getDescription());
        movie.setYearOfPublication(Integer.parseInt(movieDto.getYearOfPublication()));
        movie.setCreatedAt(movieDto.getCreatedAt());
        movie.setLikes(movieDto.getLikes());
        movie.setDislikes(movieDto.getDislikes());
        User user = userRepository.findUserByUsername(movieDto.getUsername());
        movie.setUser(user);
        return movie;
    }

    public MovieDto toMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDirector(movie.getDirector());
        movieDto.setDescription(movie.getDescription());
        movieDto.setYearOfPublication(String.valueOf(movie.getYearOfPublication()));
        movieDto.setCreatedAt(movie.getCreatedAt());
        movieDto.setLikes(movie.getLikes());
        movieDto.setDislikes(movie.getDislikes());
        movieDto.setUsername(movie.getUser().getUsername());
        return movieDto;
    }
}
