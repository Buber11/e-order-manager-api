package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EbookService {

    List<Ebook> getAllBooks();
    EbookDTOView getEbook(long ebookId);

    boolean addEbook(EbookRequest request, HttpServletRequest httpServletRequest);


}
