package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public class GetUserCommand implements Command<UserInfoResponse, UserService> {

    private final HttpServletRequest httpServletRequest;

    private GetUserCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public static GetUserCommand from(HttpServletRequest request){
        return new GetUserCommand(request);
    }

    @Override
    public UserInfoResponse execute(UserService userService) {
        return userService.getUser(httpServletRequest);
    }
}
