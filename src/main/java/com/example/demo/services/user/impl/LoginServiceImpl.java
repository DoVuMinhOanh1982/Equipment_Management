package com.example.demo.services.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.data.entities.User;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.dto.request.user.UserLoginRequest;
import com.example.demo.dto.response.user.UserLoginResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.services.user.LoginService;
import com.example.demo.utils.JwtTokenUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        String username = userLoginRequest.getUsername();
        Optional<User> userOpt = userRepository.findByUsernameAndIsDeletedFalse(username);
        if (userOpt.isEmpty()) {
            throw new BadRequestException("Username not found");
        }
        if (userOpt.get().isDeleted() == true) {
            throw new BadRequestException("User is not active");
        }
        if (!passwordEncoder.matches(userLoginRequest.getPassword(), userOpt.get().getPassword())) {
            throw new BadRequestException("Username or password is incorrect. Please try again");
        }
        String token = jwtTokenUtil.generateJwtToken(userOpt.get());
        UserLoginResponse userLoginResponse = UserLoginResponse.builder().accessToken(token).build();
        return userLoginResponse;
    }

}