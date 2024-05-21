package com.example.eordermanagerapi.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for performing CRUD operations on Client entities.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Retrieves the client ID based on the user ID.
     *
     * @param userId The ID of the user
     * @return The client ID corresponding to the user ID
     */
    @Query("SELECT c.id FROM Client c WHERE c.user.userId = ?1")
    long getClientIdByUserId(long userId);
}
