package com.example.eordermanagerapi.Fasada.commands;

import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.Service.UserService;

import java.util.List;

/*
    Use only with Fasada
 */

public class GetAllUsersCommand implements Command<List<User>, UserService> {

    private GetAllUsersCommand(){};

    // static fabric method
    public static GetAllUsersCommand from(){
        return new GetAllUsersCommand();
    }
    @Override
    public List<User> execute(UserService userService) {
        return userService.getAllUser();
    }
}
