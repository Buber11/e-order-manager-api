package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOForAuthor;
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
    public ResponseEntity getAll() {
        List<Author> authors = authorRepository.findAll();
        try {
            List<AuthorDTOView> authorDTOViews = authors.stream()
                    .map(author ->{
                        return AuthorDTOView.builder()
                                .id(author.getAuthorId())
                                .name(author.getUser().getName())
                                .surname(author.getUser().getSurname())
                                .email(author.getUser().getEmail())
                                .signUpDate(author.getSignUpDate())
                                .ebooks( author.getEbooks().stream()
                                        .map(ebook -> {
                                            return EbookDTOForAuthor.builder()
                                                    .title(ebook.getTitle())
                                                    .tag(ebook.getTag())
                                                    .image(ebook.getImage())
                                                    .id(ebook.getEbookId())
                                                    .rating(ebook.getRating())
                                                    .build();
                                        }).collect(Collectors.toList())
                                ).build();
                    }).collect(Collectors.toList());

            return buildSuccessResponse(authorDTOViews);

        }catch (Exception e){
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while fetching authors.");
        }
    }

    @Override
    public ResponseEntity get(long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isPresent()){
            Author author = authorOptional.get();
            AuthorDTOView authorDTOView = AuthorDTOView.builder()
                    .surname(author.getUser().getSurname())
                    .name(author.getUser().getName())
                    .id(author.getAuthorId())
                    .signUpDate(author.getSignUpDate())
                    .ebooks(author.getEbooks().stream()
                            .map(ebook -> {
                                return EbookDTOForAuthor.builder()
                                        .rating(ebook.getRating())
                                        .title(ebook.getTitle())
                                        .tag(ebook.getTag())
                                        .id(ebook.getEbookId())
                                        .rating(ebook.getRating())
                                        .image(ebook.getImage())
                                        .build();
                            }).collect(Collectors.toList())
                    ).build();
            return buildSuccessResponse(authorDTOView);
        }else {
            return buildErrorResponse(HttpStatus.NOT_FOUND,"Not found this author");
        }
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private <T> ResponseEntity buildSuccessResponse(T t) {
        return ResponseEntity.ok(t);
    }
}
