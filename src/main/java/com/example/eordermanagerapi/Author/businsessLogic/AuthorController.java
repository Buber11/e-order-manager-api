package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAllAuthorsCommand;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAuthorCommand;
import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetAllEbooksCommand;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * Controller class for handling author-related API endpoints.
 */
@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final Fasada fasada;

    /**
     * Constructs a new AuthorController with the specified Fasada.
     *
     * @param fasada The Fasada instance to use for handling author-related commands.
     */
    public AuthorController(Fasada fasada) {
        this.fasada = fasada;
    }

    /**
     * Retrieves all authors.
     *
     * @return ResponseEntity containing a list of AuthorDTOView objects if successful, otherwise an error response.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<AuthorDTOView> authors = fasada.handle(GetAllAuthorsCommand.from());
            return ResponseEntity.ok(authors);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching authors.");
        }
    }

    /**
     * Retrieves an author by ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return ResponseEntity containing the AuthorDTOView object if successful, otherwise an error response.
     */
    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam(name = "id") long authorId) {
        try {
            AuthorDTOView author = fasada.handle(GetAuthorCommand.from(authorId));
            return ResponseEntity.ok(author);
        } catch (EntityNotFoundException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Not found this author");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching the author.");
        }
    }

    /**
     * Builds an error response.
     *
     * @param status  The HTTP status of the error response.
     * @param message The error message to include in the response body.
     * @return ResponseEntity containing the error response.
     */
    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}


