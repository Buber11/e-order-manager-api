package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetAllEbooksCommand;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetEbookCommand;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ebook")
public class EbookController {

    private Fasada fasada;

    public EbookController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllEbooks(){
        List<Ebook> ebooks = fasada.handle(GetAllEbooksCommand.from());
        return ResponseEntity.ok(ebooks);
    }

    @GetMapping("/get")
    public ResponseEntity getEbook(@RequestParam Long ebookId){
        EbookDTOView resposne = fasada.handle(GetEbookCommand.from(ebookId));
        if(resposne != null){
            return ResponseEntity.ok(resposne);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/add")
    public ResponseEntity addEbook(@RequestBody EbookRequest request, HttpServletRequest httpServletRequest){
        return null;
    }

}
