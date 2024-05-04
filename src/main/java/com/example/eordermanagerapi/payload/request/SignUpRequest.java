package com.example.eordermanagerapi.payload.request;

public record SignUpRequest(
        String name,
        String surname,
        String password,
        String email
) {

}