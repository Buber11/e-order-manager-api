package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetTheMostPopularEbookCommand implements Command<List<EbookDTOView>, EbookService> {

    private final int amount;

    private GetTheMostPopularEbookCommand(int amount) {
        this.amount = amount;
    }
    public static GetTheMostPopularEbookCommand from(int amount){
        return new GetTheMostPopularEbookCommand(amount);
    }

    @Override
    public List<EbookDTOView> execute(EbookService ebookService) {
        return ebookService.getTheMostPopular(amount);
    }
}
