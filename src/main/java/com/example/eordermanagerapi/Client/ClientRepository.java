package com.example.eordermanagerapi.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c.id FROM Client c WHERE c.user.userId = ?1")
    long getClinetIdByUserId(long userId);

}
