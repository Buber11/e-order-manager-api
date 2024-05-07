package com.example.eordermanagerapi.auth;

import com.example.eordermanagerapi.auth.commands.LoginCommand;
import com.example.eordermanagerapi.auth.commands.RefreshTokenCommand;
import com.example.eordermanagerapi.auth.commands.SignUpCommand;
import com.example.eordermanagerapi.auth.commands.ValidateSessionCommand;
import com.example.eordermanagerapi.payload.request.SignUpRequest;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.Security.JwtService;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import com.example.eordermanagerapi.payload.response.ValidateSessionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity authenticate(@RequestBody AuthRequest request) {
        JwtResponse response = fasada.handle(LoginCommand.from(request));
        return ResponseEntity.ok(response);
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
    public ResponseEntity getRefreshedToken(HttpServletRequest request){
        Long userId = (long) request.getAttribute("id");
        JwtResponse response = fasada.handle(RefreshTokenCommand.from(userId));
        if(response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
