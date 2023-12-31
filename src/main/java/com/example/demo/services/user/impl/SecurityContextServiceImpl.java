package com.example.demo.services.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.data.entities.User;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.exceptions.ForbiddenException;
import com.example.demo.services.user.SecurityContextService;

import java.util.Optional;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    @Autowired
    private SecurityContext securityContext;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void setSecurityContext(String username) {
        Optional<User> userOptional = userRepository.findByUsernameAndIsDeletedFalse(username);
        if (userOptional.isEmpty()) {
            throw new ForbiddenException("Invalid username in JWT.");
        }
        UserDetails userDetails = new CustomUserDetails(userOptional.get());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        return ((CustomUserDetails) principal).getUser();
    }
}