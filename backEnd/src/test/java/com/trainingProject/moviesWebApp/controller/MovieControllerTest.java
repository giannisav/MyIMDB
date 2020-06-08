package com.trainingProject.moviesWebApp.controller;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.enums.SortingOrder;
import com.trainingProject.moviesWebApp.enums.SortingType;
import com.trainingProject.moviesWebApp.service.MovieService;
import com.trainingProject.moviesWebApp.service.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @Test
    public void testMovieControllerResponseEntityWithSortedMovies() {

        MovieService stubMovieService = mock(MovieService.class);
        VoteService stubVoteService = mock(VoteService.class);

        MovieDto firstMovieDto = new MovieDto();
        MovieDto secondMovieDto = new MovieDto();
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(firstMovieDto);
        movieDtoList.add(secondMovieDto);

        when(stubMovieService.findAllMoviesAndSort(SortingOrder.DESC, SortingType.LIKES)).thenReturn(movieDtoList);
        MovieController movieController = new MovieController(stubMovieService, stubVoteService);

        ResponseEntity<List<MovieDto>> responseEntity = movieController.findAllMoviesAndSort(SortingOrder.DESC, SortingType.LIKES);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(2, responseEntity.getBody().size());
    }
}
