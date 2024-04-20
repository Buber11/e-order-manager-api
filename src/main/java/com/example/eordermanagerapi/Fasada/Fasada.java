package com.example.eordermanagerapi.Fasada;

import com.example.eordermanagerapi.Fasada.commands.GetAllUsersCommand;
import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
    all services of entities in one place
 */

public class Fasada {

    UserService userService;
    @Autowired
    public Fasada(UserService theUserService){
        userService = theUserService;
    }

    public List<User> handle(GetAllUsersCommand command){
        return command.execute(userService);
    }

}
