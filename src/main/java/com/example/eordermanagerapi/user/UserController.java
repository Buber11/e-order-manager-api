package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.user.command.DeleteUserCommand;
import com.example.eordermanagerapi.user.command.GetUserCommand;
import com.example.eordermanagerapi.user.command.UpdateUserCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final Fasada fasada;

    public UserController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/get")
    public ResponseEntity getUser(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        UserInfoResponse response = fasada.handle(GetUserCommand.from(userId));
        if(response != null){
            return ResponseEntity.ok(request);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        boolean response = fasada.handle(DeleteUserCommand.from(userId));

        if(response){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(HttpServletRequest request,
                                     @RequestBody UserChangesRequest userRequest){
        Long userId = (Long) request.getAttribute("id");
        UserInfoResponse response = fasada.handle(UpdateUserCommand.from(userId, userRequest));
        if(response == null){
            return ResponseEntity.ok(request);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
