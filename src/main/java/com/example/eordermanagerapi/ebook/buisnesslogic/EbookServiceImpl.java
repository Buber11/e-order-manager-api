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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Service implementation for Ebook-related operations.
 */
@Service
//@Cacheable(cacheNames = "ebook")
public class EbookServiceImpl implements EbookService {

    private static final Logger logger = LoggerFactory.getLogger(EbookServiceImpl.class);

    private final EbookRepository ebookRepository;
    private final AuthorRepository authorRepository;

    /**
     * Constructor for EbookServiceImpl.
     * @param ebookRepository The repository for Ebook entities.
     * @param authorRepository The repository for Author entities.
     */
    public EbookServiceImpl(EbookRepository ebookRepository, AuthorRepository authorRepository) {
        this.ebookRepository = ebookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Retrieve all ebooks.
     * @return List of EbookDTOView containing all ebooks.
     */
    @Override
    @Cacheable(value = "ebooks", key = "'allBooks'")
    public List<EbookDTOView> getAllBooks() {
        logger.info("Retrieving all ebooks");
        List<Ebook> ebooks = ebookRepository.findAll();
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve an ebook by ID.
     * @param ebookId The ID of the ebook to retrieve.
     * @return EbookDTOView containing the ebook details.
     * @throws EntityNotFoundException If the ebook with the given ID is not found.
     */
    @Override
    public EbookDTOView getEbook(long ebookId) {
        System.out.println("weszło");
        logger.info("Retrieving ebook with ID: {}", ebookId);
        
        return ebookRepository.findById(ebookId)
                .map(this::mapToEbookDTOView)
                .orElseThrow(() -> new EntityNotFoundException("Ebook not found"));
    }

    /**
     * Retrieve the most popular ebooks.
     * @param amount The number of popular ebooks to retrieve.
     * @return List of EbookDTOView containing the most popular ebooks.
     */
    @Override
    public List<EbookDTOView> getTheMostPopular(int amount) {
        logger.info("Retrieving the most popular ebooks");
        List<Ebook> ebooks = ebookRepository.getTopEbooks(PageRequest.of(0, amount));
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve ebooks in alphabetical order.
     * @return List of EbookDTOView containing ebooks in alphabetical order.
     */
    @Override
    public List<EbookDTOView> getEbooksAlphabetical() {
        logger.info("Retrieving ebooks in alphabetical order");
        List<Ebook> ebooks = ebookRepository.getEbooksAlphabeticalOrder();
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    /**
     * Add a new ebook.
     * @param request The request containing ebook details.
     */
    @Override
    @CacheEvict(value = "ebooks", allEntries = true)
    public void addEbook(EbookRequest request) {
        logger.info("Adding a new ebook");
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
        logger.info("Ebook added successfully");
    }

    @Override
    public List<EbookDTOView> searchBooksByTitle(String titleQuery) {
        logger.info("Searching ebooks by title: {}", titleQuery);
        List<Ebook> ebooks = ebookRepository.findByTitleContainingIgnoreCase(titleQuery);
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    @Override
    public List<EbookDTOView> searchBooksByAuthor(String authorQuery) {
        logger.info("Searching ebooks by author: {}", authorQuery);
        List<Ebook> ebooks = ebookRepository.findByAuthorsUserSurnameContainingIgnoreCase(authorQuery);
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
    }

    @Override
    public List<EbookDTOView> searchBooksByTag(String tagQuery) {
        logger.info("Searching ebooks by tag: {}", tagQuery);
        List<Ebook> ebooks = ebookRepository.findByTagContainingIgnoreCase(tagQuery);
        return ebooks.stream()
                .map(this::mapToEbookDTOView)
                .collect(Collectors.toList());
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

}
