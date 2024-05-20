package com.example.eordermanagerapi.ebook.buisnesslogic.command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.ebook.buisnesslogic.EbookService;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class AddEbookCommnad implements Command<ResponseEntity, EbookService> {

    private EbookRequest request;

    private AddEbookCommnad(EbookRequest request) {
        this.request = request;
    }

    public static AddEbookCommnad from(EbookRequest request){
        return new AddEbookCommnad(request);
    }

    @Override
    public ResponseEntity execute(EbookService ebookService) {
        return ebookService.addEbook(request);
    }
}
