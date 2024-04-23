package com.example.eordermanagerapi.Service;

import com.example.eordermanagerapi.Entities.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    List<User> getAllUsers();
}
