package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;

public class LoginCommand implements Command<JwtResponse, AuthenticationService> {

    private final AuthRequest request;

    private LoginCommand(AuthRequest request) {
        this.request = request;
    }
    public static LoginCommand from(AuthRequest request){
        return new LoginCommand(request);
    }

    @Override
    public JwtResponse execute(AuthenticationService authenticationService) {
        return authenticationService.authenticate(request);
    }
}
