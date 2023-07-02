package com.example.demo.dto.request.user;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder

public class CreateUserRequest {
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^.*[A-Za-z].*$")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^.*[A-Za-z].*[\\s]?+.*$")
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Size(max = 24)
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$")
    @Size(max = 100, min = 8)
    private String password;
}