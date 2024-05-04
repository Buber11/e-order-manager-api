package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.payload.request.AuthRequest;

import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;

import java.util.Optional;

public interface AuthenticationService {
    JwtResponse authenticate(AuthRequest input);
    Optional<UserInfoResponse> signup(SignUpRequest request);



}
