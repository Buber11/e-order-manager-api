package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.AuthorRepository;
import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAllAuthorsCommand;
import com.example.eordermanagerapi.Author.businsessLogic.Command.GetAuthorCommand;
import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.ebook.buisnesslogic.command.GetAllEbooksCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final Fasada fasada;

    public AuthorController(Fasada fasada) {
        this.fasada = fasada;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        var authors = fasada.handle(GetAllAuthorsCommand.from());
        return ResponseEntity.ok(authors);
    }
    @GetMapping("/get")
    public ResponseEntity get(@RequestParam(name = "id")long authorId){
        var author = fasada.handle(GetAuthorCommand.from(authorId));
        if(author != null){
            return ResponseEntity.ok(author);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
