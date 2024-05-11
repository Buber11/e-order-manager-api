package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.EbookRepository;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import com.example.eordermanagerapi.payload.response.EbookResposne;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EbookService {

    List<Ebook> getAllBooks();
    EbookResposne getEbook(long ebookId);

    boolean addEbook(EbookRequest request, HttpServletRequest httpServletRequest);


}
