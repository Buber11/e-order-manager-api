package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetEbooksAlphabeticalCommand implements Command<ResponseEntity, EbookService> {

    private GetEbooksAlphabeticalCommand() {
    }
    public static GetEbooksAlphabeticalCommand from(){
        return new GetEbooksAlphabeticalCommand();
    }

    @Override
    public ResponseEntity execute(EbookService ebookService) {
        return ebookService.getEbooksAlphabetical();
    }
}
