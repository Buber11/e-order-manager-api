package com.example.eordermanagerapi.DTO.UserDTO;

public record SignUPAnswerDto(
        String name,
        String surname,
        String email,
        String sex,
        String status,
        String role
) {}
