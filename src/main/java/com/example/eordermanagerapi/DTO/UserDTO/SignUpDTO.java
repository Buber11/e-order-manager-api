package com.example.eordermanagerapi.DTO.UserDTO;

public record SignUpDTO(
        long userId,
        String password,
        String name,
        String surname,
        String email,
        String sex,
        String status,
        String role
) {}