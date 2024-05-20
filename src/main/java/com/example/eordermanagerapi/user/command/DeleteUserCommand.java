package com.example.eordermanagerapi.user.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class DeleteUserCommand implements Command<ResponseEntity, UserService> {

    private final HttpServletRequest request;

    private DeleteUserCommand(HttpServletRequest request) {
        this.request = request;
    }

    public static DeleteUserCommand from(HttpServletRequest request){
        return new DeleteUserCommand(request);
    }

    @Override
    public ResponseEntity execute(UserService userService) {
        return userService.deleteUser(request);
    }
}
