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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public JwtResponse authenticate(AuthRequest input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.email(),
                            input.password()
                    )
            );
        } catch (AuthenticationException e) {

            return null;
        }

        Optional<User> userOpt = userRepository.findByEmail(input.email());
        HashMap claims = new HashMap();
        claims.put("id",userOpt.get().getUserId());

        String jwtToken = jwtService.generateToken(claims,userOpt.get());
        return JwtResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

    }

    @Override
    public Optional<UserInfoResponse> signup(SignUpRequest request) {
        User newUser = User.builder()
                .email(request.email())
                .name(request.name())
                .surname(request.surname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(newUser);

        if(userRepository.existsByEmail(request.email())){
            return Optional.of(UserInfoResponse.builder()
                    .email(newUser.getEmail())
                    .name(newUser.getName())
                    .surname(newUser.getSurname())
                    .build());
        }else {
            return Optional.empty();
        }


    }

    @Override
    public ValidateSessionResponse getValidateSession(Long userId) {
        if(userRepository.existsById(userId)){
            return new ValidateSessionResponse(true);
        }else {
            return new ValidateSessionResponse(false);
        }
    }

    @Override
    public JwtResponse refreshToken(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            HashMap claims = new HashMap();
            claims.put("id",userId);

            String jwtToken = jwtService.generateToken(claims,userOpt.get());
            return JwtResponse.builder()
                    .token(jwtToken)
                    .expiresIn(jwtService.getExpirationTime())
                    .build();
        }else {
            return null;
        }
    }

    @Override
    public void logout(Cookie cookie) {
        String tokenStr = cookie.getValue();
        System.out.println(tokenStr);
        Token token = Token.builder()
                        .token(tokenStr)
                        .build();
        tokenRepository.save(token);
    }


}
