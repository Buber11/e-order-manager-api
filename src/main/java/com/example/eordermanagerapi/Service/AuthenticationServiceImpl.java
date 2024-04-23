package com.example.eordermanagerapi.Service;

import com.example.eordermanagerapi.DTO.LoginUserDto;
import com.example.eordermanagerapi.DTO.Mapper.UserMapper;
import com.example.eordermanagerapi.DTO.UserDTO.SignUPAnswerDto;
import com.example.eordermanagerapi.DTO.UserDTO.SignUpDTO;
import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.JPARespository.JPAUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JPAUserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(
            JPAUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public SignUPAnswerDto signup(SignUpDTO signUpDTO) {

        User newUser = userMapper.ToUser(signUpDTO);
        newUser.setPassword(passwordEncoder.encode(signUpDTO.password()));
        userRepository.save(newUser);
        SignUPAnswerDto addedUserDto = userMapper.toUserDto(newUser);

        return addedUserDto;
    }
}
