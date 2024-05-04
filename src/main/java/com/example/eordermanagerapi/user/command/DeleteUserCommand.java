package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.user.UserService;

public class DeleteUserCommand implements Command<Boolean, UserService> {

    private final Long userId;

    private DeleteUserCommand(Long userId) {
        this.userId = userId;
    }
    public static DeleteUserCommand from(Long userId){
        return new DeleteUserCommand(userId);
    }

    @Override
    public Boolean execute(UserService userService) {
        return userService.deleteUser(userId);
    }
}
