package com.example.eordermanagerapi.Service;

import com.example.eordermanagerapi.DTO.LoginUserDto;
import com.example.eordermanagerapi.DTO.UserDTO.SignUPAnswerDto;
import com.example.eordermanagerapi.DTO.UserDTO.SignUpDTO;
import com.example.eordermanagerapi.Entities.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<User> authenticate(LoginUserDto input);
    SignUPAnswerDto signup(SignUpDTO signUpDTO);

}
