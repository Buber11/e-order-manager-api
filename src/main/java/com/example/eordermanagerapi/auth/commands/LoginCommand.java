package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

/**
 * Command for handling user login.
 */
public class LoginCommand implements Command<Void, AuthenticationService> {

    private final AuthRequest request;
    private final HttpServletResponse httpServletResponse;

    /**
     * Constructs a LoginCommand with the specified authentication request and HTTP servlet response.
     *
     * @param request              the authentication request containing email and password
     * @param httpServletResponse the HTTP servlet response to add the JWT cookie to
     */
    private LoginCommand(AuthRequest request, HttpServletResponse httpServletResponse) {
        this.request = request;
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * Creates a new LoginCommand instance with the provided authentication request and HTTP servlet response.
     *
     * @param request              the authentication request containing email and password
     * @param httpServletResponse the HTTP servlet response to add the JWT cookie to
     * @return a new LoginCommand instance
     */
    public static LoginCommand from(AuthRequest request, HttpServletResponse httpServletResponse) {
        return new LoginCommand(request, httpServletResponse);
    }

    /**
     * Executes the login command by delegating the authentication process to the AuthenticationService.
     *
     * @param authenticationService the AuthenticationService to execute the command on
     * @return null
     */
    @Override
    public Void execute(AuthenticationService authenticationService) {
        authenticationService.authenticate(request, httpServletResponse);
        return null;
    }
}