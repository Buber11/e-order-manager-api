package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.payload.request.AuthRequest;

import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;

import java.util.Optional;

public interface AuthenticationService {
    JwtResponse authenticate(AuthRequest input);
    Optional<UserInfoResponse> signup(SignUpRequest request);

    ValidateSessionResponse getValidateSession(Long userId);
    JwtResponse refreshToken(Long userId);



}
