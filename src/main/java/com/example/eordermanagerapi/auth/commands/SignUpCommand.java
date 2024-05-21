package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Command for signing up a new user.
 */
public class SignUpCommand implements Command<Void, AuthenticationService> {

    private final SignUpRequest request;

    /**
     * Constructs a SignUpCommand with the specified sign-up request.
     *
     * @param request the sign-up request containing user details
     */
    private SignUpCommand(SignUpRequest request) {
        this.request = request;
    }

    /**
     * Creates a new SignUpCommand instance with the provided sign-up request.
     *
     * @param request the sign-up request containing user details
     * @return a new SignUpCommand instance
     */
    public static SignUpCommand from(SignUpRequest request) {
        return new SignUpCommand(request);
    }

    /**
     * Executes the sign-up command by delegating the user registration process to the AuthenticationService.
     *
     * @param authenticationService the AuthenticationService to execute the command on
     * @return null
     */
    @Override
    public Void execute(AuthenticationService authenticationService) {
        authenticationService.signup(request);
        return null;
    }
}
