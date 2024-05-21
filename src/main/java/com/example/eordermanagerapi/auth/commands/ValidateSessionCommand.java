package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public class ValidateSessionCommand implements Command<Boolean, AuthenticationService> {

    private final HttpServletRequest httpServletRequest;

    private ValidateSessionCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    public static ValidateSessionCommand from(HttpServletRequest httpServletRequest){
        return new ValidateSessionCommand(httpServletRequest);
    }

    @Override
    public Boolean execute(AuthenticationService authenticationService) {
        return authenticationService.getValidateSession(httpServletRequest);
    }
}
