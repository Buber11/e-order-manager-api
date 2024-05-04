package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserInfoResponse updateUser(Long userId, UserChangesRequest request) {
        User updatedUser;

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
            userRepository.save(updatedUser);
        } else {
            updatedUser = User.builder()
                    .userId(userId)
                    .name(request.name())
                    .surname(request.surname())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .build();
        }

        return UserInfoResponse.builder()
                .name(updatedUser.getName())
                .surname(updatedUser.getSurname())
                .email(updatedUser.getEmail())
                .build();
    }
}
