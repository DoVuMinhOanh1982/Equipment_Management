package com.example.demo.services.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.data.constants.EUser;
import com.example.demo.data.entities.User;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.dto.request.user.CreateUserRequest;
import com.example.demo.dto.response.user.UserResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.services.user.UserService;

import lombok.*;

@Service
@Builder
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        Optional<User> userOptional = userRepository.findByUsername(createUserRequest.getUsername());
        if (userOptional.isPresent()) {
            throw new BadRequestException("Username is already existed. Please enter a different username");
        }
        User user = userMapper.toUser(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setType(EUser.USER);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAllByIsDeletedFalse();
        return userMapper.toUserResponseList(userList);
    }
}
