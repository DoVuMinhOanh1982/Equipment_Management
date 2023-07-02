package com.example.demo.services.user;

import com.example.demo.data.entities.User;

public interface SecurityContextService {
    public void setSecurityContext(String username);

    public User getCurrentUser();
}
