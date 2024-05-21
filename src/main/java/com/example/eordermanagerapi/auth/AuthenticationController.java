package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.auth.commands.LoginCommand;
import com.example.eordermanagerapi.auth.commands.RefreshTokenCommand;
import com.example.eordermanagerapi.auth.commands.SignUpCommand;
import com.example.eordermanagerapi.auth.commands.ValidateSessionCommand;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for handling authentication-related requests.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final Fasada fasada;

    /**
     * Constructs an AuthenticationController with the specified Fasada.
     *
     * @param fasada the Fasada to be used for handling commands
     */
    public AuthenticationController(Fasada fasada) {
        this.fasada = fasada;
    }

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param authRequest         the authentication request containing email and password
     * @param httpServletResponse the HTTP servlet response to add the JWT cookie to
     * @return a ResponseEntity indicating the outcome of the authentication attempt
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest, HttpServletResponse httpServletResponse) {
        try {
            fasada.handle(LoginCommand.from(authRequest, httpServletResponse));
            return buildSuccessResponse("Authentication successful");
        } catch (AuthenticationException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during authentication");
        }
    }

    /**
     * Signs up a new user with the provided details.
     *
     * @param signUpRequest the sign-up request containing user details
     * @return a ResponseEntity indicating the outcome of the sign-up attempt
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        try {
            fasada.handle(SignUpCommand.from(signUpRequest));
            return buildSuccessResponse("Account created successfully");
        } catch (IllegalStateException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during signup");
        }
    }

    /**
     * Validates the current session based on the user ID stored in the request.
     *
     * @param httpServletRequest the HTTP servlet request containing the user ID attribute
     * @return a ResponseEntity indicating whether the session is valid
     */
    @GetMapping("/validate")
    public ResponseEntity<?> getValidateSession(HttpServletRequest httpServletRequest) {
        if (fasada.handle(ValidateSessionCommand.from(httpServletRequest))) {
            return buildSuccessResponse("Session is valid");
        } else {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Session is invalid");
        }
    }

    /**
     * Refreshes the JWT token for the user and sets it as a cookie in the response.
     *
     * @param request              the HTTP servlet request containing the user ID attribute
     * @param httpServletResponse  the HTTP servlet response to add the new JWT cookie to
     * @return a ResponseEntity indicating the outcome of the token refresh attempt
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        try {
            fasada.handle(RefreshTokenCommand.from(httpServletResponse, request));
            return buildSuccessResponse("Token refreshed successfully");
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while refreshing token");
        }
    }

    /**
     * Builds an error response with the specified status and message.
     *
     * @param status  the HTTP status to be set in the response
     * @param message the error message to be included in the response body
     * @return a ResponseEntity containing the error message
     */
    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    /**
     * Builds a success response with the specified message.
     *
     * @param message the success message to be included in the response body
     * @return a ResponseEntity containing the success message
     */
    private ResponseEntity<Map<String, String>> buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message", message));
    }
}
