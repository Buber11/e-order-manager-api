package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOForAuthor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTOView> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author ->{
                    return AuthorDTOView.builder()
                            .authorId(author.getAuthorId())
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
                                                .ebookId(ebook.getEbookId())
                                                .rating(ebook.getRating())
                                                .build();
                                    }).collect(Collectors.toList())
                            ).build();
                }).collect(Collectors.toList());
    }

    @Override
    public AuthorDTOView get(long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isPresent()){
            Author author = authorOptional.get();
            return AuthorDTOView.builder()
                    .surname(author.getUser().getSurname())
                    .name(author.getUser().getName())
                    .authorId(author.getAuthorId())
                    .signUpDate(author.getSignUpDate())
                    .ebooks(author.getEbooks().stream()
                            .map(ebook -> {
                                return EbookDTOForAuthor.builder()
                                        .rating(ebook.getRating())
                                        .title(ebook.getTitle())
                                        .tag(ebook.getTag())
                                        .ebookId(ebook.getEbookId())
                                        .rating(ebook.getRating())
                                        .image(ebook.getImage())
                                        .build();
                            }).collect(Collectors.toList())
                    ).build();
        }else {
            return null;
        }
    }
}
