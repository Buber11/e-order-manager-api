package com.example.eordermanagerapi.Author.businsessLogic.Command;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorServiceImpl;
import com.example.eordermanagerapi.Fasada.Command;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Command to retrieve all authors.
 */
public class GetAllAuthorsCommand implements Command<List<AuthorDTOView>, AuthorService> {

    private GetAllAuthorsCommand() {
    }

    /**
     * Creates a new instance of GetAllAuthorsCommand.
     *
     * @return The created GetAllAuthorsCommand instance.
     */
    public static GetAllAuthorsCommand from() {
        return new GetAllAuthorsCommand();
    }

    /**
     * Executes the command to retrieve all authors.
     *
     * @param authorService The AuthorService instance to use for retrieving authors.
     * @return A list of AuthorDTOView objects representing all authors.
     */
    @Override
    public List<AuthorDTOView> execute(AuthorService authorService) {
        return authorService.getAllAuthors();
    }
}