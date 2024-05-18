package com.example.eordermanagerapi.ebook.buisnesslogic;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.*;
import com.example.eordermanagerapi.payload.request.EbookRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ebook")
public class EbookController {

    private Fasada fasada;

    public EbookController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/get-the-most-popular")
    public ResponseEntity getTheMostPopular(@RequestParam(name ="amount")int amount){
        if(amount >= 0){
            List<EbookDTOView> ebooks = fasada.handle(GetTheMostPopularEbookCommand.from(amount));
            return ResponseEntity.ok(ebooks);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-alphabetical")
    public ResponseEntity getAlphabetical(){
        List<EbookDTOView> ebooks = fasada.handle(GetEbooksAlphabeticalCommand.from());
        return ResponseEntity.ok(ebooks);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllEbooks(){

        List<EbookDTOView> ebooks = fasada.handle(GetAllEbooksCommand.from());
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

    @PostMapping("/add")
    public ResponseEntity addEbook(@Valid @RequestBody EbookRequest request,
                                   BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = new HashMap<>();

            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);

        }

        ModelAndView modelAndView = fasada.handle(AddEbookCommnad.from(request));
        System.out.println(modelAndView);
        return ResponseEntity.status(modelAndView.getStatus()).body(modelAndView.getModel().get("message"));
    }


}
