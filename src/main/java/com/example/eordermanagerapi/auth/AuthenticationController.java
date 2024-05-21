package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.auth.commands.*;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.Security.JwtService;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.HttpCookie;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private Fasada fasada;

    public AuthenticationController(Fasada fasada) {
        this.fasada = fasada;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest, HttpServletResponse httpServletResponse) {
        try {
            fasada.handle(LoginCommand.from(authRequest,httpServletResponse));
            return buildSuccessResponse("Authentication successful");
        } catch (AuthenticationException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during authentication");
        }
    }

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

    @GetMapping("/validate")
    public ResponseEntity<?> getValidateSession(HttpServletRequest httpServletRequest) {
        if (fasada.handle(ValidateSessionCommand.from(httpServletRequest))) {
            return buildSuccessResponse("Session is valid");
        } else {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Session is invalid");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        try {
            fasada.handle(RefreshTokenCommand.from(httpServletResponse,request));
            return buildSuccessResponse("Token refreshed successfully");
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while refreshing token");
        }
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private ResponseEntity<Map<String, String>> buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message", message));
    }
}
