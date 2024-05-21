package com.example.eordermanagerapi.auth.commands;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.auth.AuthenticationService;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

/**
 * Command for refreshing the JWT token.
 */
public class RefreshTokenCommand implements Command<Void, AuthenticationService> {

    private final HttpServletResponse responseHttp;
    private final HttpServletRequest request;

    /**
     * Constructs a RefreshTokenCommand with the specified HTTP servlet response and request.
     *
     * @param responseHttp the HTTP servlet response to add the new JWT cookie to
     * @param request      the HTTP servlet request containing the user ID attribute
     */
    private RefreshTokenCommand(HttpServletResponse responseHttp, HttpServletRequest request) {
        this.responseHttp = responseHttp;
        this.request = request;
    }

    /**
     * Creates a new RefreshTokenCommand instance with the provided HTTP servlet response and request.
     *
     * @param responseHttp the HTTP servlet response to add the new JWT cookie to
     * @param request      the HTTP servlet request containing the user ID attribute
     * @return a new RefreshTokenCommand instance
     */
    public static RefreshTokenCommand from(HttpServletResponse responseHttp, HttpServletRequest request) {
        return new RefreshTokenCommand(responseHttp, request);
    }

    /**
     * Executes the refresh token command by delegating the token refresh process to the AuthenticationService.
     *
     * @param authenticationService the AuthenticationService to execute the command on
     * @return null
     */
    @Override
    public Void execute(AuthenticationService authenticationService) {
        authenticationService.refreshToken(request, responseHttp);
        return null;
    }
}