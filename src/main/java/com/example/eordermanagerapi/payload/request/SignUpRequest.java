package com.example.eordermanagerapi.payload.request;

import jakarta.validation.constraints.*;
public record SignUpRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email
) {}