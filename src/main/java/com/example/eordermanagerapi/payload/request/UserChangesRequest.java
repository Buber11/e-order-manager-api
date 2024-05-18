package com.example.eordermanagerapi.payload.request;
import jakarta.validation.constraints.*;
public record UserChangesRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Surname is required")
        String surname,
        String password,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email
) {}