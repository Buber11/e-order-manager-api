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
import org.springframework.web.servlet.ModelAndView;

import java.net.HttpCookie;
import java.util.Optional;

@RequestMapping("api/auth")
@RestController
public class AuthenticationController {
    private final Fasada fasada;
    private final AuthenticationService authenticationService;

    public AuthenticationController(Fasada fasada,
                                    AuthenticationServiceImpl authenticationService) {
        this.fasada = fasada;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody SignUpRequest request) {
        return fasada.handle(SignUpCommand.from(request));
    }
    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthRequest request, HttpServletResponse httpServletResponse) {
        return fasada.handle(LoginCommand.from(request,httpServletResponse));
    }

    @GetMapping("/validate-session")
    public ResponseEntity getValidateSestion(HttpServletRequest request){
        return fasada.handle(ValidateSessionCommand.from(request));
    }
    @GetMapping("/refresh-token")
    public ResponseEntity getRefreshedToken(HttpServletRequest request, HttpServletResponse responsehttp){

        return fasada.handle(RefreshTokenCommand.from(responsehttp,request));
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@CookieValue(name = "jwt_token")String token){
        //fasada.handle(LogoutCommand.from(cookie));
        return ResponseEntity.ok().build();
    }

}
