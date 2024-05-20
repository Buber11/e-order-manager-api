package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.auth.commands.LoginCommand;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.UnknownParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

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
    public ResponseEntity getUser(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                    .name(user.getName())
                    .surname(user.getSurname())
                    .email(user.getName())
                    .build();
            return ResponseEntity.ok(userInfoResponse);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }

    }

    @Override
    public ResponseEntity deleteUser(HttpServletRequest request) {
        long userId = (long) request.getAttribute("id");

        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user don't exist");
        }

        try {
            userRepository.deleteById(userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("user has not been deleted");
        }
    }

    @Override
    public ResponseEntity updateUser(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   UserChangesRequest request) {
        User updatedUser;
        AuthRequest authRequest;
        long userId = (long) httpServletRequest.getAttribute("id");
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
        
        return fasada.handle(LoginCommand.from(authRequest,httpServletResponse));
    }

    @Override
    public ResponseEntity existsAsAuthor(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");
        boolean exists = userRepository.existsAsAuthor(userId);

        if (exists) {
            return ResponseEntity.ok().body("this user is an author");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("this user is not an author");
        }
    }
}
