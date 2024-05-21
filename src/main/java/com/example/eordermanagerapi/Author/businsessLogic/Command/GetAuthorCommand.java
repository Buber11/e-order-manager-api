package com.example.eordermanagerapi.Author.businsessLogic.Command;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Fasada.Command;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.http.ResponseEntity;

/**
 * Command to retrieve a specific author by ID.
 */
public class GetAuthorCommand implements Command<AuthorDTOView, AuthorService> {

    private final long authorId;

    /**
     * Constructs a new GetAuthorCommand with the specified author ID.
     *
     * @param authorId The ID of the author to retrieve.
     */
    private GetAuthorCommand(long authorId) {
        this.authorId = authorId;
    }

    /**
     * Creates a new instance of GetAuthorCommand with the given author ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return The created GetAuthorCommand instance.
     */
    public static GetAuthorCommand from(long authorId) {
        return new GetAuthorCommand(authorId);
    }

    /**
     * Executes the command to retrieve the author with the specified ID.
     *
     * @param authorService The AuthorService instance to use for retrieving the author.
     * @return The AuthorDTOView representing the retrieved author.
     */
    @Override
    public AuthorDTOView execute(AuthorService authorService) {
        return authorService.getAuthorById(authorId);
    }
}
