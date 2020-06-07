package com.trainingProject.moviesWebApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    private Long id;

    @NotBlank(message = "You must insert firstName")
    @Size(max = 20, message = "Firstname must not have more than 20 characters")
    private String firstname;

    @NotBlank(message = "You must insert lastName")
    @Size(max = 20, message = "Lastname must not have more than 20 characters")
    private String lastname;

    @NotBlank(message = "You must insert email")
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = "^.+@.+\\..+$", message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "You must insert username")
    @Size(max = 20, message = "Username must not have more than 20 characters")
    private String username;

    @NotBlank(message = "You must insert password")
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
