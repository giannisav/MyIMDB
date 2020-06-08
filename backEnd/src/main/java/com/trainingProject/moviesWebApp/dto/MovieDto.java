package com.trainingProject.moviesWebApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class MovieDto {

    private Long id;

    @NotBlank(message = "You must insert movie title")
    @Size(max = 100, message = "Title must not have more than 100 characters")
    private String title;

    @NotBlank(message = "You must insert director's name")
    @Size(max = 50, message = "Director must not have more than 50 characters")
    private String director;

    @NotBlank(message = "You must insert year of publication")
    @Size(min = 4, message = "Year of publication must contain at least 4 characters")
    @Size(max = 4, message = "Year of publication must not contain more than 4 characters")
    private String yearOfPublication;

    @NotBlank(message = "You must description")
    @Size(max = 200, message = "Director must not have more than 200 characters")
    private String description;

    private String username;

    @PositiveOrZero(message = "Invalid number of likes")
    private Long likes;

    @PositiveOrZero(message = "Invalid number of dislikes")
    private Long dislikes;

    private LocalDate createdAt;

    public MovieDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

