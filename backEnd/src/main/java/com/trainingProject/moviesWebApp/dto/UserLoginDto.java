package com.trainingProject.moviesWebApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDto {

    @NotBlank(message = "You must insert username")
    private String username;
    @NotBlank(message = "You must insert password")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    public UserLoginDto(){}

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public String getPassword() {
        return password;
    }
}
