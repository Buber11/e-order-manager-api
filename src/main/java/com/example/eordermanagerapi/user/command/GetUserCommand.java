package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.user.UserService;

public class GetUserCommand implements Command<UserInfoResponse, UserService> {

    private final Long userId;
    private GetUserCommand(Long userId) {
        this.userId = userId;
    }
    public static GetUserCommand from(Long userId){
        return new GetUserCommand(userId);
    }

    @Override
    public UserInfoResponse execute(UserService userService) {
        return userService.getUser(userId);
    }
}
