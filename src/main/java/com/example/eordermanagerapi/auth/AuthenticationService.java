package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.payload.request.AuthRequest;

import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public interface AuthenticationService {
    void authenticate(AuthRequest input, HttpServletResponse httpServletResponse);
    void signup(SignUpRequest request);

    boolean getValidateSession(HttpServletRequest httpServletRequest);
    void refreshToken(HttpServletRequest request, HttpServletResponse response);
    void logout(Cookie cookie);



}
