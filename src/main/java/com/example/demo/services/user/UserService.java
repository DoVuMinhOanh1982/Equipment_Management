package com.example.demo.services.user;

import java.util.List;

import com.example.demo.dto.request.user.CreateUserRequest;
import com.example.demo.dto.response.user.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest createUserRequest);

    List<UserResponse> getAllUsers();
}
