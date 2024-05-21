package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class UpdateUserCommand implements Command<Void, UserService> {

    private final HttpServletRequest httpServletRequest;
    private final UserChangesRequest request;
    private final HttpServletResponse httpServletResponse;

    public UpdateUserCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserChangesRequest request) {
        this.httpServletRequest = httpServletRequest;
        this.request = request;
        this.httpServletResponse = httpServletResponse;
    }

    public static UpdateUserCommand from(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         UserChangesRequest request
                                         ){
        return new UpdateUserCommand(httpServletRequest, httpServletResponse, request);
    }

    @Override
    public Void execute(UserService userService) {
        userService.updateUser(httpServletRequest,httpServletResponse,request);
        return null;
    }
}
