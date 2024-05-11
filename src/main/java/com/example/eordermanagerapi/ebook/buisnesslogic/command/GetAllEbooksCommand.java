package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;

import java.util.List;

public class GetAllEbooksCommand implements Command<List<Ebook>, EbookService> {

    public static GetAllEbooksCommand from(){
        return new GetAllEbooksCommand();
    }

    @Override
    public List<Ebook> execute(EbookService ebookService) {
        return ebookService.getAllBooks();
    }
}
