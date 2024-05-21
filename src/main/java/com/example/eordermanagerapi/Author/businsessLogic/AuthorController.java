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


@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final Fasada fasada;

    public AuthorController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<AuthorDTOView> authors = fasada.handle(GetAllAuthorsCommand.from());
            return ResponseEntity.ok(authors);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching authors.");
        }
    }

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

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}


