package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.EbookRepository;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EbookServiceImpl implements EbookService {

    private final EbookRepository ebookRepository;

    public EbookServiceImpl(EbookRepository ebookRepository) {
        this.ebookRepository = ebookRepository;
    }

    @Override
    public List<Ebook> getAllBooks() {
        return ebookRepository.findAll();
    }

    @Override
    public EbookDTOView getEbook(long ebookId) {
        Optional<Ebook> ebookOpt = ebookRepository.findById(ebookId);
        if(ebookOpt.isPresent()){
            Ebook ebook = ebookOpt.get();
            return EbookDTOView.builder()
                    .tag(ebook.getTag())
                    .imagineUrl(ebook.getImagineUrl())
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
    public boolean addEbook(EbookRequest request, HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");
        return true;
    }
}
