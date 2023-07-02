package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.user.UserLoginRequest;
import com.example.demo.dto.response.user.UserLoginResponse;
import com.example.demo.services.user.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                loginService.login(userLoginRequest));
    }
}