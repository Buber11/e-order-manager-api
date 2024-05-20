package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.Security.JwtService;

import com.example.eordermanagerapi.auth.token.Token;
import com.example.eordermanagerapi.auth.token.TokenRepository;
import com.example.eordermanagerapi.payload.request.AuthRequest;

import com.example.eordermanagerapi.payload.request.SignUpRequest;

import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import com.example.eordermanagerapi.user.User;
import com.example.eordermanagerapi.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
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

    public ResponseEntity<Map<String, String>> authenticate(AuthRequest input, HttpServletResponse httpServletResponse) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.email(),
                            input.password()
                    )
            );
        } catch (AuthenticationException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");
        }

        Optional<User> userOpt = userRepository.findByEmail(input.email());
        if (userOpt.isEmpty()) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = userOpt.get();
        String jwtToken = jwtService.generateToken(Map.of("id", user.getUserId()), user);
        Cookie cookie = createJwtCookie(jwtToken);
        httpServletResponse.addCookie(cookie);

        return buildSuccessResponse("Authentication successful");
    }
    @Override
    public ResponseEntity signup(SignUpRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST,"An account with this email already exists");
        }

        User newUser = User.builder()
                .email(request.email())
                .name(request.name())
                .surname(request.surname())
                .password(passwordEncoder.encode(request.password()))
                .build();

        User savedUser = userRepository.save(newUser);

        if (Long.valueOf(savedUser.getUserId()) != null) {
            UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                    .email(savedUser.getEmail())
                    .name(savedUser.getName())
                    .surname(savedUser.getSurname())
                    .build();

            return buildSuccessResponse("account built succesfully");
        } else {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"something went wrong");
        }
    }

    @Override
    public ResponseEntity getValidateSession(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");

        if(userRepository.existsById(userId)){
            return buildSuccessResponse("Session is valid");
        }else {
            return buildSuccessResponse("Session is invalid");
        }

    }

    @Override
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        long userId = (long) request.getAttribute("id");
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED,"User not found");
        }

        User user = userOpt.get();
        String jwtToken = jwtService.generateToken(Map.of("id", userId), user);

        Cookie cookie = createJwtCookie(jwtToken);
        httpServletResponse.addCookie(cookie);

        return buildSuccessResponse("Token refreshed successfully");
    }

    @Override
    public void logout(Cookie cookie) {
        String tokenStr = cookie.getValue();
        Token token = Token.builder()
                        .token(tokenStr)
                        .build();
        tokenRepository.save(token);
    }

    private Cookie createJwtCookie(String jwtToken) {
        Cookie cookie = new Cookie("jwt_token", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(5 * 3600);
        return cookie;
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private ResponseEntity<Map<String, String>> buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message", message));
    }

}
