package com.example.eordermanagerapi.Fasada;

import com.example.eordermanagerapi.DTO.UserDTO.SignUPAnswerDto;
import com.example.eordermanagerapi.Fasada.commands.CreateNewUserCommand;
import com.example.eordermanagerapi.Fasada.commands.GetAllUsersCommand;
import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.Service.AuthenticationService;
import com.example.eordermanagerapi.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/*
    all services of entities in one place
 */

public class Fasada {

    private UserServiceImpl userService;

    private final AuthenticationService authenticationService;

    public Fasada(AuthenticationService theAuthenticationService) {
        authenticationService = theAuthenticationService;
    }

    public List<User> handle(GetAllUsersCommand command){
        return command.execute(userService);
    }

    public SignUPAnswerDto handle(CreateNewUserCommand command){
        return command.execute(authenticationService);
    }

    @Autowired
    @Lazy
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

}
