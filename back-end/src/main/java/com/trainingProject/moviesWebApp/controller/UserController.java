package com.trainingProject.moviesWebApp.controller;


import com.trainingProject.moviesWebApp.dto.UserDto;
import com.trainingProject.moviesWebApp.dto.UserLoginDto;
import com.trainingProject.moviesWebApp.exceptions.NotExistingUserException;
import com.trainingProject.moviesWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Boolean> login(@Valid @RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok().body(userService.authenticate(userLoginDto));
    }

    @PostMapping("users/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.register(userDto));
    }


    @ExceptionHandler(NotExistingUserException.class)
    public ResponseEntity<String> notExistingUser() {
        return ResponseEntity.ok().body("Wrong Username or Password. Try Again!");
    }

}

