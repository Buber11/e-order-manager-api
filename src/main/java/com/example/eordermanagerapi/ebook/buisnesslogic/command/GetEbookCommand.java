package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.EbookRepository;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import com.example.eordermanagerapi.payload.response.EbookResposne;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

public class GetEbookCommand implements Command<EbookResposne, EbookService> {

    private final long ebookId;

    private GetEbookCommand(long ebookId) {
        this.ebookId = ebookId;
    }

    public static GetEbookCommand from(long ebookId){
        return new GetEbookCommand(ebookId);
    }

    @Override
    public EbookResposne execute(EbookService ebookService) {
        return ebookService.getEbook(ebookId);
    }
}
