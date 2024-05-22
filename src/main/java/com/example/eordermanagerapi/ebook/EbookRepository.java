package com.example.eordermanagerapi.ebook;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Retrieves ebooks containing the specified title (case-insensitive).
     * @param titleQuery The query string to search for in titles.
     * @return A list of ebooks containing the specified title.
     */
    List<Ebook> findByTitleContainingIgnoreCase(String titleQuery);

    /**
     * Retrieves ebooks written by authors with the specified surname (case-insensitive).
     * @param authorSurname The query string to search for in author surnames.
     * @return A list of ebooks written by authors with the specified surname.
     */
    @Query("SELECT e FROM Ebook e JOIN e.authors a WHERE LOWER(a.user.surname) LIKE %:authorSurname%")
    List<Ebook> findByAuthorsUserSurnameContainingIgnoreCase(@Param("authorSurname") String authorSurname);

    /**
     * Retrieves ebooks containing the specified tag (case-insensitive).
     * @param tagQuery The query string to search for in tags.
     * @return A list of ebooks containing the specified tag.
     */
    List<Ebook> findByTagContainingIgnoreCase(String tagQuery);

}