package com.example.demo.services.user;

import com.example.demo.dto.request.user.UserLoginRequest;
import com.example.demo.dto.response.user.UserLoginResponse;

public interface LoginService {
    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
