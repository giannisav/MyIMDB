package com.trainingProject.moviesWebApp.entity;

import javax.persistence.*;

@Entity(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieId")
    private Long movieId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "yearOfPublication", nullable = false, length = 4)
    private String yearOfPublication;

    @Column(name = "directorName", nullable = false, length = 200)
    private String directorName;

    public Movie() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}