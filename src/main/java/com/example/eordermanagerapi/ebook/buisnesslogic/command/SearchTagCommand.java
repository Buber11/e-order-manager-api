package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;

import java.util.List;

public class SearchTagCommand implements Command<List<EbookDTOView>, EbookService> {

    private final String tagQuery;

    public SearchTagCommand(String tagQuery) {
        this.tagQuery = tagQuery;
    }

    public static SearchTagCommand from(String tagQuery) {
        return new SearchTagCommand(tagQuery);
    }

    @Override
    public List<EbookDTOView> execute(EbookService ebookService) {
        return ebookService.searchBooksByTag(tagQuery);
    }
}


