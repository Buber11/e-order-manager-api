package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.user.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class ExistsAsAuthorCommand implements Command<Boolean, UserService> {

    private final HttpServletRequest httpServletRequest;

    private ExistsAsAuthorCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    public static ExistsAsAuthorCommand from(HttpServletRequest httpServletRequest){
        return new ExistsAsAuthorCommand(httpServletRequest);
    }

    @Override
    public Boolean execute(UserService userService) {
        return userService.existsAsAuthor(httpServletRequest);
    }
}
