package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOForAuthor;
import com.example.eordermanagerapi.ebook.Ebook;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTOView> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::mapToAuthorDTOView)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTOView getAuthorById(long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        return authorOptional.map(this::mapToAuthorDTOView)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
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
