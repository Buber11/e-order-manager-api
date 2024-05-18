package com.example.eordermanagerapi.Author.businsessLogic.Command;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorServiceImpl;
import com.example.eordermanagerapi.Fasada.Command;

import java.util.List;

public class GetAllAuthorsCommand implements Command<List<AuthorDTOView>, AuthorService> {

    private GetAllAuthorsCommand() {
    }

    public static GetAllAuthorsCommand from(){
        return new GetAllAuthorsCommand();
    }

    @Override
    public List<AuthorDTOView> execute(AuthorService authorService) {
        return authorService.getAll();
    }
}