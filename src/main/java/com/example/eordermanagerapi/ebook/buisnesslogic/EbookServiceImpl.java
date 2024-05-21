package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import com.example.eordermanagerapi.additionalContent.AdditionalContent;
import com.example.eordermanagerapi.additionalContent.AdditionalContentDTOView;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.EbookRepository;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Cacheable(cacheNames = "ebook")
public class EbookServiceImpl implements EbookService {

    private final EbookRepository ebookRepository;
    private final AuthorRepository authorRepository;

    public EbookServiceImpl(EbookRepository ebookRepository, AuthorRepository authorRepository) {
        this.ebookRepository = ebookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Cacheable(value = "ebooks",key = "'allBooks'")
    public List<EbookDTOView> getAllBooks(){
        List<Ebook> ebooks = ebookRepository.findAll();
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "ebook",key = "#ebookId")
    public EbookDTOView getEbook(long ebookId){
        return ebookRepository.findById(ebookId)
                .map(this::mapToEbookDTOView)
                .orElseThrow(() -> new EntityNotFoundException("Ebook not found"));
    }

    @Override
    public List<EbookDTOView> getTheMostPopular(int amount){
        List<Ebook> ebooks = ebookRepository.getTopEbooks(PageRequest.of(0, amount));
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    @Override
    public List<EbookDTOView> getEbooksAlphabetical(){
        List<Ebook> ebooks = ebookRepository.getEbooksAlphabeticalOrder();
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "ebooks", allEntries = true)
    public void addEbook(EbookRequest request) {
        Ebook ebook = Ebook.builder()
                .title(request.title())
                .tag(request.tag())
                .rating(request.rating())
                .image(request.imageUrl())
                .mainContent(request.mainContent())
                .price(request.price())
                .authors(request.author().stream()
                        .map(this::findAuthorById)
                        .collect(Collectors.toCollection(LinkedList::new)))
                .build();

        List<AdditionalContent> additionalContents = request.contentURL().stream()
                .map(url -> AdditionalContent.builder()
                        .ebook(ebook)
                        .contentURL(url)
                        .build())
                .collect(Collectors.toList());

        ebook.setAdditionalContent(additionalContents);
        ebookRepository.save(ebook);

    }

    private EbookDTOView mapToEbookDTOView(Ebook ebook) {
        return EbookDTOView.builder()
                .id(ebook.getEbookId())
                .tag(ebook.getTag())
                .title(ebook.getTitle())
                .rating(ebook.getRating())
                .image(ebook.getImage())
                .mainContent(ebook.getMainContent())
                .price(ebook.getPrice())
                .authors(ebook.getAuthors().stream()
                        .map(this::mapToAuthorDTOForEbook)
                        .collect(Collectors.toList()))
                .additionalContents(ebook.getAdditionalContent().stream()
                        .map(this::mapToAdditionalContentDTOView)
                        .collect(Collectors.toList()))
                .build();
    }

    private AuthorDTOForEbook mapToAuthorDTOForEbook(Author author) {
        return AuthorDTOForEbook.builder()
                .id(author.getAuthorId())
                .surname(author.getUser().getSurname())
                .name(author.getUser().getName())
                .email(author.getUser().getEmail())
                .signUpDate(author.getSignUpDate())
                .build();
    }

    private AdditionalContentDTOView mapToAdditionalContentDTOView(AdditionalContent additionalContent) {
        return AdditionalContentDTOView.builder()
                .contentURL(additionalContent.getContentURL())
                .contentId(additionalContent.getContentId())
                .build();
    }

    private Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author with id " + authorId + " not found"));
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
