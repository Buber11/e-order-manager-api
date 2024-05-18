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
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
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
    public List<EbookDTOView> getAllBooks() {
        List<Ebook> ebooks = ebookRepository.findAll();

        return ebooks.stream()
                .map(ebook -> {
                    return EbookDTOView.builder()
                            .ebookId(ebook.getEbookId())
                            .tag(ebook.getTag())
                            .title(ebook.getTitle())
                            .rating(ebook.getRating())
                            .image(ebook.getImage())
                            .authors( ebook.getAuthors().stream()
                                    .map(author -> {
                                        return AuthorDTOForEbook.builder()
                                                .authorId(author.getAuthorId())
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
    }

    @Override
    public EbookDTOView getEbook(long ebookId) {
        Optional<Ebook> ebookOpt = ebookRepository.findById(ebookId);
        if(ebookOpt.isPresent()){
            Ebook ebook = ebookOpt.get();
            return EbookDTOView.builder()
                    .tag(ebook.getTag())
                    .image(ebook.getImage())
                    .title(ebook.getTitle())
                    .ebookId(ebook.getEbookId())
                    .rating(ebook.getRating())
                    .authors( ebook.getAuthors().stream()
                            .map(author -> {
                               return AuthorDTOForEbook.builder()
                                        .email(author.getUser().getEmail())
                                        .name(author.getUser().getName())
                                        .authorId(author.getAuthorId())
                                        .surname(author.getUser().getSurname())
                                        .signUpDate(author.getSignUpDate())
                                        .build();
                            } )
                            .collect(Collectors.toList())
                    )
                    .build();
        }else {
            return null;
        }

    }

    @Override
    public List<EbookDTOView> getTheMostPopular(int amount) {
        List<Ebook> ebooks = ebookRepository.getTopEbooks(PageRequest.of(0,amount));
        return ebooks.stream()
                .map(ebook -> {
                    return EbookDTOView.builder()
                            .ebookId(ebook.getEbookId())
                            .tag(ebook.getTag())
                            .title(ebook.getTitle())
                            .rating(ebook.getRating())
                            .image(ebook.getImage())
                            .authors( ebook.getAuthors().stream()
                                    .map(author -> {
                                        return AuthorDTOForEbook.builder()
                                                .authorId(author.getAuthorId())
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
    }

    @Override
    public List<EbookDTOView> getEbooksAlphabetical() {
        List<Ebook> ebooks = ebookRepository.getEbooksAlphabeticalOrder();

        return ebooks.stream()
                .map(ebook -> {
                    return EbookDTOView.builder()
                            .ebookId(ebook.getEbookId())
                            .tag(ebook.getTag())
                            .title(ebook.getTitle())
                            .rating(ebook.getRating())
                            .image(ebook.getImage())
                            .authors( ebook.getAuthors().stream()
                                    .map(author -> {
                                        return AuthorDTOForEbook.builder()
                                                .authorId(author.getAuthorId())
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

    }

    @Override
    public ModelAndView addEbook(EbookRequest request) {
        ModelAndView modelAndView = new ModelAndView();
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
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            modelAndView.addObject("message",e.getMessage());
            return modelAndView;
        }

        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}
