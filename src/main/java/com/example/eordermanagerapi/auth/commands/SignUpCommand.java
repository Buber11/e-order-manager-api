package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;

import java.util.Optional;

public class SignUpCommand implements Command<Optional<UserInfoResponse>, AuthenticationService> {

    private final SignUpRequest request;

    private SignUpCommand(SignUpRequest request) {
        this.request = request;
    }
    public static SignUpCommand from(SignUpRequest request){
        return new SignUpCommand(request);
    }

    @Override
    public Optional<UserInfoResponse> execute(AuthenticationService authenticationService) {
        return authenticationService.signup(request);
    }
}
