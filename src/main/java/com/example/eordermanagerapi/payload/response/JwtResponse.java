package com.example.eordermanagerapi.payload.response;

import lombok.Builder;

@Builder
public record JwtResponse (
    String token,
    long expiresIn

){}
