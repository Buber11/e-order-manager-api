package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.auth.AuthenticationServiceImpl;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOForAuthor;
import com.example.eordermanagerapi.ebook.Ebook;
import jakarta.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for handling author-related operations.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Retrieves all authors.
     *
     * @return A list of AuthorDTOView objects representing all authors.
     */
    @Override
    public List<AuthorDTOView> getAllAuthors() {
        logger.info("Retrieving all authors");
        List<AuthorDTOView> authors = authorRepository.findAll().stream()
                .map(this::mapToAuthorDTOView)
                .collect(Collectors.toList());
        logger.info("Retrieved {} authors", authors.size());
        return authors;
    }

    /**
     * Retrieves an author by ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return An AuthorDTOView object representing the author with the specified ID.
     * @throws EntityNotFoundException if no author with the given ID exists.
     */
    @Override
    public AuthorDTOView getAuthorById(long authorId) {
        logger.info("Retrieving author with ID {}", authorId);
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        AuthorDTOView authorDTOView = authorOptional.map(this::mapToAuthorDTOView)
                .orElseThrow(() -> {
                    logger.error("Author with ID {} not found", authorId);
                    return new EntityNotFoundException("Author not found");
                });
        logger.info("Retrieved author with ID {}: {}", authorId, authorDTOView);
        return authorDTOView;
    }

    private AuthorDTOView mapToAuthorDTOView(Author author) {
        return AuthorDTOView.builder()
                .id(author.getAuthorId())
                .name(author.getUser().getName())
                .surname(author.getUser().getSurname())
                .email(author.getUser().getEmail())
                .signUpDate(author.getSignUpDate())
                .ebooks(author.getEbooks().stream()
                        .map(this::mapToEbookDTOForAuthor)
                        .collect(Collectors.toList()))
                .build();
    }

    private EbookDTOForAuthor mapToEbookDTOForAuthor(Ebook ebook) {
        return EbookDTOForAuthor.builder()
                .id(ebook.getEbookId())
                .title(ebook.getTitle())
                .tag(ebook.getTag())
                .image(ebook.getImage())
                .rating(ebook.getRating())
                .build();
    }
}
