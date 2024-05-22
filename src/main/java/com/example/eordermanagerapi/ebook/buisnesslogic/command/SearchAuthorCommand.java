package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;

import java.util.List;

public class SearchAuthorCommand implements Command<List<EbookDTOView>, EbookService> {

    private final String authorQuery;

    public SearchAuthorCommand(String authorQuery) {
        this.authorQuery = authorQuery;
    }

    public static SearchAuthorCommand from(String authorQuery) {
        return new SearchAuthorCommand(authorQuery);
    }

    @Override
    public List<EbookDTOView> execute(EbookService ebookService) {
        return ebookService.searchBooksByAuthor(authorQuery);
    }
}

