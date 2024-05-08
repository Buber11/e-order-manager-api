package com.example.eordermanagerapi.payload.request;

public record AuthRequest (
        String email,
        String password
){}