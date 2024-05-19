package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.auth.commands.*;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.Security.JwtService;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpCookie;
import java.util.Optional;

@RequestMapping("api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final Fasada fasada;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService,
                                    Fasada fasada,
                                    AuthenticationServiceImpl authenticationService) {
        this.jwtService = jwtService;
        this.fasada = fasada;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody SignUpRequest request) {
        Optional<UserInfoResponse> response = fasada.handle(SignUpCommand.from(request));

        if(response.isPresent()){
            return ResponseEntity.ok(response.get());
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthRequest request, HttpServletResponse responsehttp) {
        System.out.println("weszło");
        JwtResponse response = fasada.handle(LoginCommand.from(request));

        Cookie cookie = new Cookie("jwt_token", response.token());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(5*3600);
        responsehttp.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate-session")
    public ResponseEntity getValidateSestion(HttpServletRequest request){
        Long userId = (long) request.getAttribute("id");
        ValidateSessionResponse response = fasada.handle(ValidateSessionCommand.from(userId));

        if(response.validation()){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/refresh-token")
    public ResponseEntity getRefreshedToken(HttpServletRequest request, HttpServletResponse responsehttp){
        Long userId = (long) request.getAttribute("id");
        JwtResponse response = fasada.handle(RefreshTokenCommand.from(userId));
        
        Cookie cookie = new Cookie("jwt_token", response.token());
        cookie.setHttpOnly(true);
        responsehttp.addCookie(cookie);

        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@CookieValue(name = "jwt_token")String token){
        //fasada.handle(LogoutCommand.from(cookie));
        return ResponseEntity.ok().build();
    }

}
