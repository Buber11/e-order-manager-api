package com.example.eordermanagerapi.Controller;

import com.example.eordermanagerapi.DTO.UserDTO.LoginResponse;
import com.example.eordermanagerapi.DTO.LoginUserDto;
import com.example.eordermanagerapi.DTO.UserDTO.SignUPAnswerDto;
import com.example.eordermanagerapi.DTO.UserDTO.SignUpDTO;
import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.Fasada.commands.CreateNewUserCommand;
import com.example.eordermanagerapi.Security.JwtService;
import com.example.eordermanagerapi.Service.AuthenticationService;
import com.example.eordermanagerapi.Service.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
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
    public ResponseEntity<SignUPAnswerDto> register(@RequestBody SignUpDTO signUpDTO) {
        SignUPAnswerDto createdUserDto = fasada.handle(CreateNewUserCommand.from(signUpDTO));
        return ResponseEntity.ok(createdUserDto);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        System.out.println("weszło");
        Optional<User> authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser.get());
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
