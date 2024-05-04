package com.example.eordermanagerapi.Fasada;


import com.example.eordermanagerapi.auth.commands.LoginCommand;
import com.example.eordermanagerapi.auth.commands.SignUpCommand;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.user.UserServiceImpl;
import com.example.eordermanagerapi.user.command.DeleteUserCommand;
import com.example.eordermanagerapi.user.command.GetUserCommand;
import com.example.eordermanagerapi.user.command.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Optional;

/*
    all services of entities in one place
 */

public class Fasada {

    private UserServiceImpl userService;

    private final AuthenticationService authenticationService;

    public Fasada(AuthenticationService theAuthenticationService) {
        authenticationService = theAuthenticationService;
    }

   public JwtResponse handle(LoginCommand command){
        return command.execute(authenticationService);
   }
   public Optional<UserInfoResponse> handle(SignUpCommand command){
        return command.execute(authenticationService);
   }
   public UserInfoResponse handle(GetUserCommand command){
        return command.execute(userService);
   }
   public Boolean handle(DeleteUserCommand command){
        return command.execute(userService);
   }
   public UserInfoResponse handle(UpdateUserCommand command){
        return command.execute(userService);
   }

    @Autowired
    @Lazy
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

}
