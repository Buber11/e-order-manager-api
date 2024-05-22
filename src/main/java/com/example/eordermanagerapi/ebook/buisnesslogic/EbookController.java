package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.*;
import com.example.eordermanagerapi.order.businessLogic.Command.AddOrderCommand;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handling ebook-related HTTP requests.
 */
@RestController
@RequestMapping("/api/ebook")
public class EbookController {

    private Fasada fasada;

    public EbookController(Fasada fasada) {
        this.fasada = fasada;
    }

    /**
     * Retrieves the most popular ebooks.
     *
     * @param amount The number of ebooks to retrieve.
     * @return ResponseEntity containing a list of EbookDTOView objects.
     */
    @GetMapping("/get-the-most-popular")
    public ResponseEntity<?> getTheMostPopular(@RequestParam(name ="amount") int amount) {
        try {
            if (amount >= 0) {
                List<EbookDTOView> ebooks = fasada.handle(GetTheMostPopularEbookCommand.from(amount));
                return buildSuccessResponse(ebooks);
            } else {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid amount");
            }
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching the most popular ebooks.");
        }
    }

    /**
     * Retrieves ebooks in alphabetical order.
     *
     * @return ResponseEntity containing a list of EbookDTOView objects.
     */
    @GetMapping("/get-alphabetical")
    public ResponseEntity<?> getAlphabetical() {
        try {
            List<EbookDTOView> ebooks = fasada.handle(GetEbooksAlphabeticalCommand.from());
            return buildSuccessResponse(ebooks);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching ebooks.");
        }
    }

    /**
     * Retrieves all ebooks.
     *
     * @return ResponseEntity containing a list of EbookDTOView objects.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEbooks() {
        try {
            List<EbookDTOView> ebooks = fasada.handle(GetAllEbooksCommand.from());
            return buildSuccessResponse(ebooks);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching ebooks. " + e.getMessage());
        }
    }

    /**
     * Retrieves a specific ebook by its ID.
     *
     * @param ebookId The ID of the ebook to retrieve.
     * @return ResponseEntity containing the EbookDTOView object.
     */
    @GetMapping("/get")
    public ResponseEntity<?> getEbook(@RequestParam("id") Long ebookId) {
        try {
            EbookDTOView ebook = fasada.handle(GetEbookCommand.from(ebookId));
            return buildSuccessResponse(ebook);
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching the ebook.");
        }
    }

    /**
     * Adds a new ebook.
     *
     * @param request       The EbookRequest object containing the details of the new ebook.
     * @param bindingResult The result of the validation process.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addEbook(@Valid @RequestBody EbookRequest request,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation errors", errorsMap);
        }

        try {
            fasada.handle(AddEbookCommnad.from(request));
            return buildSuccessResponse("The ebook has been added");
        } catch (RuntimeException e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while adding the ebook: " + e.getMessage());
        }
    }

    /**
     * Searches ebooks by title.
     *
     * @param titleQuery The query string to search for in titles.
     * @return ResponseEntity containing the search results.
     */
    @GetMapping("/searchTitle")
    public ResponseEntity<?> searchBooksByTitle(@RequestParam("query") String titleQuery) {
        try {
            List<EbookDTOView> searchResults = fasada.handle(SearchTitleCommand.from(titleQuery));
            return buildSuccessResponse(searchResults);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while searching ebooks by title: " + e.getMessage());
        }
    }

    /**
     * Searches ebooks by author.
     *
     * @param authorQuery The query string to search for in author names.
     * @return ResponseEntity containing the search results.
     */
    @GetMapping("/searchAuthor")
    public ResponseEntity<?> searchBooksByAuthor(@RequestParam("query") String authorQuery) {
        try {
            List<EbookDTOView> searchResults = fasada.handle(SearchAuthorCommand.from(authorQuery));
            return buildSuccessResponse(searchResults);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while searching ebooks by author: " + e.getMessage());
        }
    }

    /**
     * Searches ebooks by tag.
     *
     * @param tagQuery The query string to search for in tags.
     * @return ResponseEntity containing the search results.
     */
    @GetMapping("/searchTag")
    public ResponseEntity<?> searchBooksByTag(@RequestParam("query") String tagQuery) {
        try {
            List<EbookDTOView> searchResults = fasada.handle(SearchTagCommand.from(tagQuery));
            return buildSuccessResponse(searchResults);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while searching ebooks by tag: " + e.getMessage());
        }
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message, Map<String, String> validationErrors) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        errorResponse.put("validationErrors", validationErrors);
        return ResponseEntity.status(status).body(errorResponse);
    }

    private <T> ResponseEntity<T> buildSuccessResponse(T body) {
        return ResponseEntity.ok(body);
    }

    private ResponseEntity<Map<String, String>> buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message", message));
    }
}


