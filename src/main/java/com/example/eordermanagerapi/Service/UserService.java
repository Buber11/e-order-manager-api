package com.example.eordermanagerapi.Service;

import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.JPARespository.JPAUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final JPAUserRepository userRepository;



    public UserService( JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }



}
