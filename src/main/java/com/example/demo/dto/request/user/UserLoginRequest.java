package com.example.demo.dto.request.user;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
public class UserLoginRequest {
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Size(max = 24)
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$")
    @Size(max = 100, min = 8)
    private String password;
}
