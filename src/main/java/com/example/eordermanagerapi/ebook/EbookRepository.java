package com.example.eordermanagerapi.ebook;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Repository interface for Ebook entities.
 */
@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    /**
     * Retrieves a list of top ebooks based on their rating.
     * @param pageable Pagination information.
     * @return A list of ebooks sorted by rating in descending order.
     */
    @Query("SELECT e FROM Ebook e ORDER BY e.rating DESC")
    List<Ebook> getTopEbooks(Pageable pageable);

    /**
     * Retrieves a list of ebooks sorted alphabetically by title.
     * @return A list of ebooks sorted alphabetically by title.
     */
    @Query("SELECT e FROM Ebook e ORDER BY e.title ASC")
    List<Ebook> getEbooksAlphabeticalOrder();
}