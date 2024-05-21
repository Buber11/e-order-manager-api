package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import org.springframework.http.ResponseEntity;

public class GetEbookCommand implements Command<EbookDTOView, EbookService> {

    private final long ebookId;

    private GetEbookCommand(long ebookId) {
        this.ebookId = ebookId;
    }

    public static GetEbookCommand from(long ebookId){
        return new GetEbookCommand(ebookId);
    }

    @Override
    public EbookDTOView execute(EbookService ebookService) {
        return ebookService.getEbook(ebookId);
    }
}
