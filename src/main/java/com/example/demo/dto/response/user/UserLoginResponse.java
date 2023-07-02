package com.example.demo.dto.response.user;

import lombok.*;

@Getter
@Setter
@Builder

public class UserLoginResponse {
    private String accessToken;
}
