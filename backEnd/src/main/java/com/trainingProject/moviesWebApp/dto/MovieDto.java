package com.trainingProject.moviesWebApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MovieDto {

    private Long id;
    @NotBlank(message = "You must insert movie name")
    private String name;
    @NotBlank(message = "You must insert director's name")
    private String directorsName;
    @NotBlank(message = "You must insert year of publication")
    @Size(min = 4, message = "Year of publication must contain at least 4 characters")
    @Size(max = 4, message = "Year of publication must not contain more than 4 characters")
    private String yearOfPublication;

    public MovieDto(){}

    public MovieDto(Long id, String name, String directorsName, String yearOfPublication) {
        this.id = id;
        this.name = name;
        this.directorsName = directorsName;
        this.yearOfPublication = yearOfPublication;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}

