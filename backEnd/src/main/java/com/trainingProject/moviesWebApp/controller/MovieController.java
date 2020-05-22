package com.trainingProject.moviesWebApp.controller;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.exceptions.NotExistingMovieException;
import com.trainingProject.moviesWebApp.exceptions.NotExistingUserException;
import com.trainingProject.moviesWebApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("")
public class MovieController {

    private MovieService movieService;
    private long id;

    @Autowired
    public MovieController (MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/home")
    ResponseEntity<List<MovieDto>> getMovies() {
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

    @GetMapping("/movies/{id}")
    ResponseEntity<MovieDto> getMovieById(@PathVariable long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @PostMapping("/movies/save")
    ResponseEntity<MovieDto> saveMovie(@Valid @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok().body(movieService.save(movieDto));
    }

    @DeleteMapping("/movies/delete/{id}")
    ResponseEntity<List<MovieDto>> deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
        return getMovies();
    }

    @ExceptionHandler(NotExistingMovieException.class)
    public ResponseEntity<String> notExistingUser() {
        return ResponseEntity.ok().body("Wrong movie_id. Try again!");
    }
}
