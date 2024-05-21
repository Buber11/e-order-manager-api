package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.auth.commands.LoginCommand;
import com.example.eordermanagerapi.payload.request.AuthRequest;
import com.example.eordermanagerapi.payload.request.UserChangesRequest;
import com.example.eordermanagerapi.payload.response.JwtResponse;
import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import jakarta.persistence.EntityNotFoundException;
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
    public UserInfoResponse getUser(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return UserInfoResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void deleteUser(HttpServletRequest request) {
        long userId = (long) request.getAttribute("id");

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User doesn't exist");
        }

        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   UserChangesRequest request) {
        long userId = (long) httpServletRequest.getAttribute("id");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.surname() != null) {
            user.setSurname(request.surname());
        }
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (request.password() != null) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        userRepository.save(user);

        AuthRequest authRequest = new AuthRequest(user.getEmail(), request.password());
        fasada.handle(LoginCommand.from(authRequest, httpServletResponse));
    }

    @Override
    public boolean existsAsAuthor(HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");
        return userRepository.existsAsAuthor(userId);
    }
}