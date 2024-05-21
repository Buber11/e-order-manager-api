package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Interface for authentication-related operations.
 */
public interface AuthenticationService {

    /**
     * Authenticates a user based on the provided credentials and sets a JWT token as a cookie in the response.
     *
     * @param input                the authentication request containing email and password
     * @param httpServletResponse the HTTP servlet response to add the JWT cookie to
     */
    void authenticate(AuthRequest input, HttpServletResponse httpServletResponse);

    /**
     * Signs up a new user with the provided details.
     *
     * @param request the sign-up request containing user details
     */
    void signup(SignUpRequest request);

    /**
     * Validates the current session based on the user ID stored in the request.
     *
     * @param httpServletRequest the HTTP servlet request containing the user ID attribute
     * @return true if the session is valid, false otherwise
     */
    boolean getValidateSession(HttpServletRequest httpServletRequest);

    /**
     * Refreshes the JWT token for the user and sets it as a cookie in the response.
     *
     * @param request              the HTTP servlet request containing the user ID attribute
     * @param response             the HTTP servlet response to add the new JWT cookie to
     */
    void refreshToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * Logs out the user by invalidating the JWT token.
     *
     * @param cookie the JWT token cookie to invalidate
     */
    void logout(Cookie cookie);
}
