package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.Security.JwtService;

import com.example.eordermanagerapi.auth.token.Token;
import com.example.eordermanagerapi.auth.token.TokenRepository;
import com.example.eordermanagerapi.payload.request.AuthRequest;

import com.example.eordermanagerapi.payload.request.SignUpRequest;

import com.example.eordermanagerapi.user.User;
import com.example.eordermanagerapi.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for handling authentication-related operations.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     TokenRepository tokenRepository,
                                     JwtService jwtService,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Authenticates a user and sets a JWT token as a cookie in the response.
     *
     * @param input                the authentication request containing email and password
     * @param httpServletResponse  the HTTP servlet response to add the JWT cookie to
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public void authenticate(AuthRequest input, HttpServletResponse httpServletResponse) throws AuthenticationException {
        logger.info("Attempting to authenticate user with email: {}", input.email());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.email(), input.password())
            );

            User user = userRepository.findByEmail(input.email())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            String jwtToken = jwtService.generateToken(Map.of("id", user.getUserId()), user);
            Cookie cookie = createJwtCookie(jwtToken);
            httpServletResponse.addCookie(cookie);
            logger.info("User authenticated successfully with email: {}", input.email());
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for email: {}", input.email(), e);
            throw e;
        }
    }

    /**
     * Signs up a new user.
     *
     * @param request the sign-up request containing user details
     * @throws IllegalStateException if an account with the given email already exists
     */
    @Override
    public void signup(SignUpRequest request) {
        logger.info("Attempting to sign up user with email: {}", request.email());

        if (userRepository.existsByEmail(request.email())) {
            logger.error("An account with email {} already exists", request.email());
            throw new IllegalStateException("An account with this email already exists");
        }

        User newUser = User.builder()
                .email(request.email())
                .name(request.name())
                .surname(request.surname())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(newUser);
        logger.info("User signed up successfully with email: {}", request.email());
    }

    /**
     * Validates the current session based on the user ID stored in the request.
     *
     * @param httpServletRequest the HTTP servlet request containing the user ID attribute
     * @return true if the session is valid, false otherwise
     */
    @Override
    public boolean getValidateSession(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");
        boolean exists = userRepository.existsById(userId);

        logger.info("Validating session for user ID: {}. Session valid: {}", userId, exists);

        return exists;
    }

    /**
     * Refreshes the JWT token for the user and sets it as a cookie in the response.
     *
     * @param request              the HTTP servlet request containing the user ID attribute
     * @param httpServletResponse  the HTTP servlet response to add the new JWT cookie to
     * @throws EntityNotFoundException if the user is not found
     */
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) throws EntityNotFoundException {
        long userId = (long) request.getAttribute("id");
        logger.info("Attempting to refresh token for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(Map.of("id", userId), user);
        Cookie cookie = createJwtCookie(jwtToken);
        httpServletResponse.addCookie(cookie);

        logger.info("Token refreshed successfully for user ID: {}", userId);
    }

    /**
     * Logs out the user by invalidating the JWT token.
     *
     * @param cookie the JWT token cookie to invalidate
     */
    @Override
    public void logout(Cookie cookie) {
        String tokenStr = cookie.getValue();
        logger.info("Logging out user with token: {}", tokenStr);

        Token token = Token.builder()
                .token(tokenStr)
                .build();
        tokenRepository.save(token);

        logger.info("User logged out and token invalidated");
    }

    /**
     * Creates a JWT cookie.
     *
     * @param jwtToken the JWT token to set in the cookie
     * @return the created cookie
     */
    private Cookie createJwtCookie(String jwtToken) {
        Cookie cookie = new Cookie("jwt_token", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(5 * 3600);
        return cookie;
    }
}
