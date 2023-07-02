package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.data.entities.User;
import com.example.demo.dto.request.user.CreateUserRequest;
import com.example.demo.dto.response.user.UserResponse;

@Component
public class UserMapper {
    public User toUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .username(createUserRequest.getUsername())
                .isDeleted(false).build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .type(user.getType())
                .isDeleted(user.isDeleted())
                .build();
    }

    public List<UserResponse> toUserResponseList(List<User> userList) {
        return userList.stream().map(this::toUserResponse).collect(Collectors.toList());
    }
}
