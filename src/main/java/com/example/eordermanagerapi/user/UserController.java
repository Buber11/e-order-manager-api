package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.user.command.DeleteUserCommand;
import com.example.eordermanagerapi.user.command.ExistsAsAuthorCommand;
import com.example.eordermanagerapi.user.command.GetUserCommand;
import com.example.eordermanagerapi.user.command.UpdateUserCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final Fasada fasada;

    public UserController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/get")
    public ResponseEntity getUser(HttpServletRequest request){
        return fasada.handle(GetUserCommand.from(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(HttpServletRequest request){

        return fasada.handle(DeleteUserCommand.from(request));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(HttpServletRequest httpServletRequest,
                                     @RequestBody UserChangesRequest userRequest,
                                     HttpServletResponse httpServletResponse){

        return fasada.handle(UpdateUserCommand.from(httpServletRequest,httpServletResponse,userRequest));
    }
    @GetMapping("/exists-as-author")
    public ResponseEntity existsAsAuthor(HttpServletRequest httpServletRequest){
        return fasada.handle(ExistsAsAuthorCommand.from(httpServletRequest));
    }
}
