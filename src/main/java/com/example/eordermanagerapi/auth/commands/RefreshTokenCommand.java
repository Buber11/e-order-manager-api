package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.JwtResponse;

public class RefreshTokenCommand implements Command<JwtResponse, AuthenticationService> {

    private Long userId;

    private RefreshTokenCommand(Long userId) {
        this.userId = userId;
    }
    public static RefreshTokenCommand from(Long userId){
        return new RefreshTokenCommand(userId);
    }

    @Override
    public JwtResponse execute(AuthenticationService authenticationService) {
        return authenticationService.refreshToken(userId);
    }
}
