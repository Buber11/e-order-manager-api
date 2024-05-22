package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Service interface for Ebook-related operations.
 */
public interface EbookService {

    /**
     * Retrieves all ebooks.
     *
     * @return List of EbookDTOView objects representing all ebooks.
     */
    List<EbookDTOView> getAllBooks();

    /**
     * Retrieves a specific ebook by its ID.
     *
     * @param ebookId The ID of the ebook to retrieve.
     * @return EbookDTOView object representing the specified ebook.
     */
    EbookDTOView getEbook(long ebookId);

    /**
     * Retrieves the most popular ebooks.
     *
     * @param amount The number of ebooks to retrieve.
     * @return List of EbookDTOView objects representing the most popular ebooks.
     */
    List<EbookDTOView> getTheMostPopular(int amount);

    /**
     * Retrieves ebooks in alphabetical order.
     *
     * @return List of EbookDTOView objects representing ebooks in alphabetical order.
     */
    List<EbookDTOView> getEbooksAlphabetical();

    /**
     * Adds a new ebook.
     *
     * @param request The EbookRequest object containing the details of the new ebook.
     */
    void addEbook(EbookRequest request);

    /**
     * Searches ebooks by title.
     *
     * @param titleQuery The query string to search for in titles.
     * @return List of EbookDTOView objects representing ebooks matching the title query.
     */
    List<EbookDTOView> searchBooksByTitle(String titleQuery);

    /**
     * Searches ebooks by author.
     *
     * @param authorQuery The query string to search for in authors.
     * @return List of EbookDTOView objects representing ebooks written by authors matching the query.
     */
    List<EbookDTOView> searchBooksByAuthor(String authorQuery);

    /**
     * Searches ebooks by tag.
     *
     * @param tagQuery The query string to search for in tags.
     * @return List of EbookDTOView objects representing ebooks with tags matching the query.
     */
    List<EbookDTOView> searchBooksByTag(String tagQuery);
}
