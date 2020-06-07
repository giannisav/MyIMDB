package com.trainingProject.moviesWebApp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VoteDto {

    @Pattern(regexp = "^(UPVOTE|DOWNVOTE)$", message = "Invalid vote")
    private String rate;

    @NotNull(message = "movie_id cant be null")
    private long movie_id;

    @NotNull(message = "user_id cant be null")
    private long user_id;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}

