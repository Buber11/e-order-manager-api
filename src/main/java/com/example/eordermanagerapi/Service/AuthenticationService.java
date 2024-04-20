package com.example.eordermanagerapi.Service;

import com.example.eordermanagerapi.Entities.LoginUserDto;
import com.example.eordermanagerapi.Entities.RegisterUserDto;
import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.JPARespository.JPAUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final JPAUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            JPAUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User(passwordEncoder.encode(input.getPassword()),"Kam","Płyta",input.getEmail(),"M","A","CLIENT");
        return userRepository.save(user);
    }

    public Optional<User> authenticate(LoginUserDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println("Błąd uwierzytelniania: " + e.getMessage());
            return null;
        }
        return userRepository.findByEmail(input.getEmail());
    }
}
