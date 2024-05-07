package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;

public class ValidateSessionCommand implements Command<ValidateSessionResponse, AuthenticationService> {

    Long userId;

    private ValidateSessionCommand(Long userId) {
        this.userId = userId;
    }
    public static ValidateSessionCommand from(Long userId){
        return new ValidateSessionCommand(userId);
    }

    @Override
    public ValidateSessionResponse execute(AuthenticationService authenticationService) {
        return authenticationService.getValidateSession(userId);
    }
}
