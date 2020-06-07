package com.trainingProject.moviesWebApp.controller;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.dto.VoteDto;
import com.trainingProject.moviesWebApp.enums.SortingOrder;
import com.trainingProject.moviesWebApp.enums.SortingType;
import com.trainingProject.moviesWebApp.exceptions.NotExistingMovieException;
import com.trainingProject.moviesWebApp.exceptions.NotExistingVoteException;
import com.trainingProject.moviesWebApp.exceptions.SpamVoteException;
import com.trainingProject.moviesWebApp.service.MovieService;
import com.trainingProject.moviesWebApp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping()
public class MovieController {

    private MovieService movieService;
    private VoteService voteService;

    @Autowired
    public MovieController(MovieService movieService, VoteService voteService) {
        this.movieService = movieService;
        this.voteService = voteService;
    }

    @GetMapping("/movies")
    ResponseEntity<List<MovieDto>> findMovies(@RequestParam(value = "sortingOrder") SortingOrder sortingOrder,
                                              @RequestParam(value = "sortingType") SortingType sortingType) {
        return ResponseEntity.ok().body(movieService.findAllMoviesAndSort(sortingOrder, sortingType));
    }

    @GetMapping("/movies/{id}")
    ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @GetMapping("/movies/user/{id}")
    ResponseEntity<List<MovieDto>> getMoviesByUserId(@PathVariable("id") Long id,
                                                     @RequestParam(value = "sortingOrder") SortingOrder sortingOrder,
                                                     @RequestParam(value = "sortingType") SortingType sortingType) {
        List<MovieDto> movieList = movieService.findAndSortMoviesByUser_Id(id, sortingOrder, sortingType);
        return ResponseEntity.ok().body(movieList);
    }

    @PostMapping("/movies/save")
    ResponseEntity<MovieDto> saveMovie(@Valid @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok().body(movieService.save(movieDto));
    }

    @PostMapping("/movies/vote")
    public ResponseEntity<MovieDto> vote(@Valid @RequestBody VoteDto voteDto) {
        return ResponseEntity.ok().body(voteService.vote(voteDto));
    }

    @DeleteMapping("/movies/delete/{id}")
    ResponseEntity<List<MovieDto>> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().body(movieService.getAllMovies());
    }

//    @ExceptionHandler(SpamVoteException.class)
//    public ResponseEntity<String> cantSpamVotes() {
//        return ResponseEntity.ok().body("You are not allowed to vote the same movie many times.You can change your vote if you want.");
//    }
//
//    @ExceptionHandler(NotExistingVoteException.class)
//    public ResponseEntity<String> voteDoesNotExists() {
//        return ResponseEntity.ok().body("Vote does not exists.");
//    }
//
//    @ExceptionHandler(NotExistingMovieException.class)
//    public ResponseEntity<String> notExistingUser() {
//        return ResponseEntity.ok().body("Wrong movie_id. Try again!");
//    }
}
