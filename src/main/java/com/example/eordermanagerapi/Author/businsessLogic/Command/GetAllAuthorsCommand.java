package com.example.eordermanagerapi.Author.businsessLogic.Command;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorServiceImpl;
import com.example.eordermanagerapi.Fasada.Command;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetAllAuthorsCommand implements Command<ResponseEntity, AuthorService> {

    private GetAllAuthorsCommand() {
    }

    public static GetAllAuthorsCommand from(){
        return new GetAllAuthorsCommand();
    }

    @Override
    public ResponseEntity execute(AuthorService authorService) {
        return authorService.getAll();
    }
}
