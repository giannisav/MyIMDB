package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.MovieDto;
import com.trainingProject.moviesWebApp.dto.VoteDto;

public interface VoteService {
    MovieDto vote(VoteDto voteDto);

    MovieDto deleteVote(VoteDto voteDto);
}

