package com.trainingProject.moviesWebApp.service;

import com.trainingProject.moviesWebApp.dto.UserDto;
import com.trainingProject.moviesWebApp.dto.UserLoginDto;
import com.trainingProject.moviesWebApp.entity.User;
import com.trainingProject.moviesWebApp.exceptions.NotExistingUserException;
import com.trainingProject.moviesWebApp.mapper.UserMapper;
import com.trainingProject.moviesWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.repository = userRepository;
        this.mapper = userMapper;
    }

    @Override
    public UserDto authenticate(UserLoginDto userLoginDto) {
        User user = Optional.ofNullable(repository.findUserByUsername(userLoginDto.getUsername())).orElseThrow(() -> new NotExistingUserException("Wrong credentials"));
        if(userLoginDto.getPassword().equals(user.getPassword())) {
            return mapper.userToUserDto(user);
        }
        throw new NotExistingUserException("Wrong credentials");
    }

    @Override
    public UserDto register(UserDto userDto) {
        User newUser = mapper.userDtoToUser(userDto);
        return mapper.userToUserDto(repository.save(newUser));
    }
}
