package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.user.UserService;

public class UpdateUserCommand implements Command<JwtResponse, UserService> {

    private final long userId;
    private final UserChangesRequest request;

    private UpdateUserCommand(long userId, UserChangesRequest request) {
        this.userId = userId;
        this.request = request;
    }
    public static UpdateUserCommand from(long userId, UserChangesRequest request){
        return new UpdateUserCommand(userId,request);
    }

    @Override
    public JwtResponse execute(UserService userService) {
        return userService.updateUser(userId,request);
    }
}
