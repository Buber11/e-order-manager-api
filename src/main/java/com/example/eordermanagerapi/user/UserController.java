package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.user.command.DeleteUserCommand;
import com.example.eordermanagerapi.user.command.ExistsAsAuthorCommand;
import com.example.eordermanagerapi.user.command.GetUserCommand;
import com.example.eordermanagerapi.user.command.UpdateUserCommand;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Fasada fasada;

    public UserController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/getInfo")
    public ResponseEntity<?> getUser(HttpServletRequest httpServletRequest) {
        try {
            UserInfoResponse userInfo = fasada.handle(GetUserCommand.from(httpServletRequest));
            return ResponseEntity.ok(userInfo);
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching the user.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        try {
            fasada.handle(DeleteUserCommand.from(request));
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting the user.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        @RequestBody UserChangesRequest request) {
        try {
            fasada.handle(UpdateUserCommand.from(httpServletRequest,httpServletResponse,request));
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while updating the user.");
        }
    }

    @GetMapping("/author")
    public ResponseEntity<?> existsAsAuthor(HttpServletRequest httpServletRequest) {
        try {
            boolean isAuthor = fasada.handle(ExistsAsAuthorCommand.from(httpServletRequest));
            return isAuthor ? ResponseEntity.ok().body("This user is an author") :
                    ResponseEntity.status(HttpStatus.NO_CONTENT).body("This user is not an author");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while checking author status.");
        }
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}

