package com.trainingProject.moviesWebApp.entity;

import com.trainingProject.moviesWebApp.enums.Rate;

import javax.persistence.*;

@Entity(name = "vote")
public class Vote {

    @EmbeddedId
    private UserMovieKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "rate")
    @Enumerated(EnumType.STRING)
    private Rate rate;

    public Vote() {
    }

    public UserMovieKey getId() {
        return id;
    }

    public void setId(UserMovieKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }
}

