package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetAllEbooksCommand implements Command<List<EbookDTOView>, EbookService> {

    public static GetAllEbooksCommand from(){
        return new GetAllEbooksCommand();
    }

    @Override
    public List<EbookDTOView> execute(EbookService ebookService) {
        return ebookService.getAllBooks();
    }
}
