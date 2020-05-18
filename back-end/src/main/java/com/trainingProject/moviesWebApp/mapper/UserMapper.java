package com.trainingProject.moviesWebApp.mapper;

import com.trainingProject.moviesWebApp.dto.UserDto;
import com.trainingProject.moviesWebApp.dto.UserLoginDto;
import com.trainingProject.moviesWebApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
