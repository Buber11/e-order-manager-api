package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class LoginCommand implements Command<ResponseEntity, AuthenticationService> {

    private final AuthRequest request;
    private final HttpServletResponse httpServletResponse;

    private LoginCommand(AuthRequest request,HttpServletResponse httpServletResponse) {
        this.request = request;
        this.httpServletResponse = httpServletResponse;
    }
    public static LoginCommand from(AuthRequest request,HttpServletResponse httpServletResponse){
        return new LoginCommand(request,httpServletResponse);
    }

    @Override
    public ResponseEntity execute(AuthenticationService authenticationService) {
        return authenticationService.authenticate(request,httpServletResponse);
    }
}
