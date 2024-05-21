package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Service interface for handling author-related operations.
 */
public interface AuthorService {

    /**
     * Retrieves all authors.
     *
     * @return A list of AuthorDTOView objects representing all authors.
     */
    List<AuthorDTOView> getAllAuthors();

    /**
     * Retrieves an author by ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return An AuthorDTOView object representing the author with the specified ID.
     */
    AuthorDTOView getAuthorById(long authorId);
}