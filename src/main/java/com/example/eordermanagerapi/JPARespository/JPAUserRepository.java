package com.example.eordermanagerapi.JPARespository;

import com.example.eordermanagerapi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAUserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);

}
