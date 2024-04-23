package com.example.eordermanagerapi.Fasada.commands;

import com.example.eordermanagerapi.DTO.UserDTO.SignUPAnswerDto;
import com.example.eordermanagerapi.DTO.UserDTO.SignUpDTO;
import com.example.eordermanagerapi.Service.AuthenticationService;

public class CreateNewUserCommand implements Command<SignUPAnswerDto, AuthenticationService> {

    private  SignUpDTO signUpDTO;
    private CreateNewUserCommand(SignUpDTO theSignUpDTO){
        signUpDTO = theSignUpDTO;
    }
    public static CreateNewUserCommand from(SignUpDTO signUpDTO){
        return new CreateNewUserCommand(signUpDTO);
    }
    @Override
    public SignUPAnswerDto execute(AuthenticationService authenticationService) {
        return authenticationService.signup(signUpDTO);
    }
}
