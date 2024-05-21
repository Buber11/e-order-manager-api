package com.example.eordermanagerapi.Author;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on Author entities.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
