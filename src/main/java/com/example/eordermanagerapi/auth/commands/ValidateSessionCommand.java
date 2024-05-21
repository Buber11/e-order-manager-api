package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;


/**
 * Command for validating a user session.
 */
public class ValidateSessionCommand implements Command<Boolean, AuthenticationService> {

    private final HttpServletRequest httpServletRequest;

    /**
     * Constructs a ValidateSessionCommand with the specified HTTP servlet request.
     *
     * @param httpServletRequest the HTTP servlet request containing the user session information
     */
    private ValidateSessionCommand(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * Creates a new ValidateSessionCommand instance with the provided HTTP servlet request.
     *
     * @param httpServletRequest the HTTP servlet request containing the user session information
     * @return a new ValidateSessionCommand instance
     */
    public static ValidateSessionCommand from(HttpServletRequest httpServletRequest) {
        return new ValidateSessionCommand(httpServletRequest);
    }

    /**
     * Executes the session validation command by delegating the validation process to the AuthenticationService.
     *
     * @param authenticationService the AuthenticationService to execute the command on
     * @return true if the session is valid, false otherwise
     */
    @Override
    public Boolean execute(AuthenticationService authenticationService) {
        return authenticationService.getValidateSession(httpServletRequest);
    }
}
