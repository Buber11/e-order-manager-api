package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);


}
