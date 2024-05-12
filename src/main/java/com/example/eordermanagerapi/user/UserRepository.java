package com.example.eordermanagerapi.user;

import com.example.eordermanagerapi.payload.response.UserInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("SELECT EXISTS (SELECT 1 FROM authors WHERE user_id = ?1 )")
    boolean existsAsAuthor(long userId);

}
