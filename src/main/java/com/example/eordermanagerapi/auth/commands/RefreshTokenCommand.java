package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class RefreshTokenCommand implements Command<ResponseEntity, AuthenticationService> {

    private HttpServletResponse responsehttp;
    private HttpServletRequest request;

    private RefreshTokenCommand(HttpServletResponse responsehttp, HttpServletRequest request) {
        this.responsehttp = responsehttp;
        this.request = request;
    }
    public static RefreshTokenCommand from(HttpServletResponse responsehttp, HttpServletRequest request){
        return new RefreshTokenCommand(responsehttp,request);
    }

    @Override
    public ResponseEntity execute(AuthenticationService authenticationService) {
        return authenticationService.refreshToken(request,responsehttp);
    }
}
