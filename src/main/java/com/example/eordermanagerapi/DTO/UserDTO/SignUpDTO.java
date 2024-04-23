package com.example.eordermanagerapi.DTO.UserDTO;

public record SignUpDTO(
        String password,
        String name,
        String surname,
        String email,
        String sex,
        String status,
        String role
) {}