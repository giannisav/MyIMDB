package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.UserDto;
import com.trainingProject.moviesWebApp.dto.UserLoginDto;

public interface UserService {
    boolean authenticate (UserLoginDto userLoginDto);
    UserDto register(UserDto userDto);
}
