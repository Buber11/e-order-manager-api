package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.EbookRepository;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.mapping.Collection;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EbookServiceImpl implements EbookService {

    private final EbookRepository ebookRepository;
    private final AuthorRepository authorRepository;

    public EbookServiceImpl(EbookRepository ebookRepository, AuthorRepository authorRepository) {
        this.ebookRepository = ebookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public ResponseEntity getAllBooks() {
        List<Ebook> ebooks = ebookRepository.findAll();

        try {
            List<EbookDTOView> ebookDTOViews = ebooks.stream()
                    .map(ebook -> {
                        return EbookDTOView.builder()
                                .id(ebook.getEbookId())
                                .tag(ebook.getTag())
                                .title(ebook.getTitle())
                                .rating(ebook.getRating())
                                .image(ebook.getImage())
                                .authors( ebook.getAuthors().stream()
                                        .map(author -> {
                                            return AuthorDTOForEbook.builder()
                                                    .id( author.getAuthorId() )
                                                    .surname(author.getUser().getSurname())
                                                    .name(author.getUser().getName())
                                                    .email(author.getUser().getEmail())
                                                    .signUpDate(author.getSignUpDate())
                                                    .build();
                                        })
                                        .collect(Collectors.toList())
                                )
                                .build();

                    })
                    .collect(Collectors.toList());
            return buildSuccessResponse(ebookDTOViews);
        }catch (Exception e){
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while fetching ebooks.");
        }
    }

    @Override
    public ResponseEntity getEbook(long ebookId) {
        Optional<Ebook> ebookOpt = ebookRepository.findById(ebookId);
        if(ebookOpt.isPresent()){
            Ebook ebook = ebookOpt.get();
            EbookDTOView ebookDTOView = EbookDTOView.builder()
                    .tag(ebook.getTag())
                    .image(ebook.getImage())
                    .title(ebook.getTitle())
                    .id(ebook.getEbookId())
                    .rating(ebook.getRating())
                    .authors( ebook.getAuthors().stream()
                            .map(author -> {
                               return AuthorDTOForEbook.builder()
                                        .email(author.getUser().getEmail())
                                        .name(author.getUser().getName())
                                        .id(author.getAuthorId())
                                        .surname(author.getUser().getSurname())
                                        .signUpDate(author.getSignUpDate())
                                        .build();
                            } )
                            .collect(Collectors.toList())
                    )
                    .build();
            return buildSuccessResponse(ebookDTOView);
        }else {
            return buildErrorResponse(HttpStatus.NOT_FOUND,"Not found this ebook");
        }

    }

    @Override
    public ResponseEntity getTheMostPopular(int amount) {
        List<Ebook> ebooks = ebookRepository.getTopEbooks(PageRequest.of(0,amount));
        try {
            List<EbookDTOView> ebookDTOViews = ebooks.stream()
                    .map(ebook -> {
                        return EbookDTOView.builder()
                                .id(ebook.getEbookId())
                                .tag(ebook.getTag())
                                .title(ebook.getTitle())
                                .rating(ebook.getRating())
                                .image(ebook.getImage())
                                .authors( ebook.getAuthors().stream()
                                        .map(author -> {
                                            return AuthorDTOForEbook.builder()
                                                    .id(author.getAuthorId())
                                                    .surname(author.getUser().getSurname())
                                                    .name(author.getUser().getName())
                                                    .email(author.getUser().getEmail())
                                                    .signUpDate(author.getSignUpDate())
                                                    .build();
                                        })
                                        .collect(Collectors.toList())
                                )
                                .build();

                    })
                    .collect(Collectors.toList());
            return buildSuccessResponse(ebookDTOViews);
        }catch (Exception e){
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while fetching ebooks.");
        }
    }

    @Override
    public ResponseEntity getEbooksAlphabetical() {
        List<Ebook> ebooks = ebookRepository.getEbooksAlphabeticalOrder();

        try {
            List<EbookDTOView> ebookDTOViews = ebooks.stream()
                    .map(ebook -> {
                        return EbookDTOView.builder()
                                .id(ebook.getEbookId())
                                .tag(ebook.getTag())
                                .title(ebook.getTitle())
                                .rating(ebook.getRating())
                                .image(ebook.getImage())
                                .authors( ebook.getAuthors().stream()
                                        .map(author -> {
                                            return AuthorDTOForEbook.builder()
                                                    .id(author.getAuthorId())
                                                    .surname(author.getUser().getSurname())
                                                    .name(author.getUser().getName())
                                                    .email(author.getUser().getEmail())
                                                    .signUpDate(author.getSignUpDate())
                                                    .build();
                                        })
                                        .collect(Collectors.toList())
                                )
                                .build();

                    })
                    .collect(Collectors.toList());
            return buildSuccessResponse(ebookDTOViews);
        }catch (Exception e){
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"an error accured while fetching ebooks.");
        }

    }

    @Override
    public ResponseEntity addEbook(EbookRequest request) {
        try {
           Ebook.builder()
                    .title(request.title())
                    .tag(request.tag())
                    .rating(request.rating())
                    .image(request.imagineUrl())
                    .authors(request.author().stream()
                            .map(e -> {
                                if (authorRepository.existsById(e)) {
                                    return authorRepository.getReferenceById(e);
                                } else {
                                    throw new RuntimeException("Author with id " + e + " not found");
                                }
                            })
                            .collect(Collectors.toCollection(LinkedList::new))
                    )
                    .build();
        } catch (RuntimeException e) {

            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"an error occured while adding book.");
        }

        return buildSuccessResponse("the ebooks has been added");
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private <T> ResponseEntity buildSuccessResponse(T t) {
        return ResponseEntity.ok(t);
    }
    private  ResponseEntity buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message",message));
    }
}
