package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import jakarta.servlet.http.Cookie;

public class LogoutCommand implements Command<Void, AuthenticationService> {

    private final Cookie cookie;

    private LogoutCommand(Cookie cookie) {
        this.cookie = cookie;
    }

    public static LogoutCommand from(Cookie cookie){
        return new LogoutCommand(cookie);
    }

    @Override
    public Void execute(AuthenticationService authenticationService) {
        authenticationService.logout(cookie);
        return null;
    }
}
