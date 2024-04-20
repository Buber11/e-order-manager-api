package com.example.eordermanagerapi.Service;

import com.example.eordermanagerapi.Entities.User;
import com.example.eordermanagerapi.JPARespository.JPAUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    JPAUserRepository jpaUserRepository;

    public UserService(JPAUserRepository theJpaUserRepository){
        this.jpaUserRepository = theJpaUserRepository;
    }

    public void saveUser(User user){
        jpaUserRepository.save(user);
    }
    public List<User> getAllUser(){
        return jpaUserRepository.findAll();
    }

}
