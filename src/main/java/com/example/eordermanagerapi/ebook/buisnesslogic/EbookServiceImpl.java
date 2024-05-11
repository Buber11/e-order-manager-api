package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.EbookRepository;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import com.example.eordermanagerapi.payload.response.EbookResposne;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public EbookResposne getEbook(long ebookId) {
        Optional<Ebook> ebookOpt = ebookRepository.findById(ebookId);
        if(ebookOpt.isPresent()){
            Ebook ebook = ebookOpt.get();
            return EbookResposne.builder()
                    .title(ebook.getTitle())
                    .tag(ebook.getTag())
                    .author(ebook.getAuthor())
                    .imagineUrl(ebook.getImagineUrl())
                    .rating(ebook.getRating())
                    .build();
        }else {
            return null;
        }

    }

    @Override
    public boolean addEbook(EbookRequest request, HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");

    }
}
