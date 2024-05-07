package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.auth.commands.LoginCommand;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import org.hibernate.query.UnknownParameterException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Fasada fasada;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Fasada fasada) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fasada = fasada;
    }

    @Override
    public UserInfoResponse getUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            return UserInfoResponse.builder()
                    .name(user.getName())
                    .surname(user.getSurname())
                    .email(user.getName())
                    .build();
        }else {
            return null;
        }

    }

    @Override
    public Boolean deleteUser(Long userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public JwtResponse updateUser(Long userId, UserChangesRequest request) {
        User updatedUser;
        AuthRequest authRequest;

        if(request.name() == null
                && request.surname() == null
                && request.email() == null
                && request.password() == null
        ){
            return null;
        } else if(request.password() == null){
            Optional<User> userOpt = userRepository.findById(userId);
            updatedUser = userOpt.get();
            updatedUser = updatedUser
                    .setEmail(request.email())
                    .setName(request.name())
                    .setSurname(request.surname());

            authRequest = new AuthRequest(updatedUser.getEmail(),updatedUser.getPassword());

        } else {
            updatedUser = User.builder()
                    .userId(userId)
                    .name(request.name())
                    .surname(request.surname())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .build();

            authRequest = new AuthRequest(updatedUser.getEmail(),updatedUser.getPassword());
        }
        userRepository.save(updatedUser);
        
        return fasada.handle(LoginCommand.from(authRequest));
    }
}
