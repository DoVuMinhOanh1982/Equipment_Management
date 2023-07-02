package com.example.demo.dto.response.user;

import com.example.demo.data.constants.EUser;

import lombok.*;

@Builder
@Getter
@Setter
public class UserResponse {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private EUser type;
    private boolean isDeleted;
}
