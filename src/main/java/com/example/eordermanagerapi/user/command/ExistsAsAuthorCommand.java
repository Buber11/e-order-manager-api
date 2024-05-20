package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class ExistsAsAuthorCommand implements Command<ResponseEntity, UserService> {

    private final HttpServletRequest httpServletRequest;

    private ExistsAsAuthorCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    public static ExistsAsAuthorCommand from(HttpServletRequest httpServletRequest){
        return new ExistsAsAuthorCommand(httpServletRequest);
    }
    @Override
    public ResponseEntity execute(UserService userService) {
        return userService.existsAsAuthor(httpServletRequest);
    }
}
