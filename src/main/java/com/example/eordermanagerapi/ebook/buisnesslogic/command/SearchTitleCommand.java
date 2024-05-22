package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;

import java.util.List;

public class SearchTitleCommand implements Command<List<EbookDTOView>, EbookService> {

    private final String titleQuery;

    public SearchTitleCommand(String titleQuery) {
        this.titleQuery = titleQuery;
    }

    public static SearchTitleCommand from(String titleQuery) {
        return new SearchTitleCommand(titleQuery);
    }

    @Override
    public List<EbookDTOView> execute(EbookService ebookService) {
        return ebookService.searchBooksByTitle(titleQuery);
    }
}