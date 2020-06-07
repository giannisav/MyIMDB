package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.UserDto;
import com.trainingProject.moviesWebApp.dto.UserLoginDto;

import java.util.List;

public interface UserService {
    UserDto authenticate(UserLoginDto userLoginDto);

    UserDto register(UserDto userDto);

    List<UserDto> getUsers();
}
