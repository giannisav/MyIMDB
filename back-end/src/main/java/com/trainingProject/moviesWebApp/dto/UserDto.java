package com.trainingProject.moviesWebApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    private Long id;
    @NotBlank(message = "You must insert firstName")
    private String firstName;
    @NotBlank(message = "You must insert lastName")
    private String lastName;
    @NotBlank(message = "You must insert username")
    private String username;
    @NotBlank(message = "You must insert password")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    public UserDto(){}

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
