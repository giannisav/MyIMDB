package com.trainingProject.moviesWebApp.controller;


import com.trainingProject.moviesWebApp.dto.UserDto;
import com.trainingProject.moviesWebApp.dto.UserLoginDto;
import com.trainingProject.moviesWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@Valid @RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok().body(userService.authenticate(userLoginDto));
    }

    @PostMapping("users/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.register(userDto));
    }
}

