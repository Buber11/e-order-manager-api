package com.example.eordermanagerapi.Author.businsessLogic.Command;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.Author.businsessLogic.AuthorService;
import com.example.eordermanagerapi.Fasada.Command;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.http.ResponseEntity;

public class GetAuthorCommand implements Command<AuthorDTOView, AuthorService> {

    private final long authorId;

    private GetAuthorCommand(long authorId) {
        this.authorId = authorId;
    }

    public static GetAuthorCommand from(long authorId){
        return new GetAuthorCommand(authorId);
    }

    @Override
    public AuthorDTOView execute(AuthorService authorService) {
        return authorService.getAuthorById(authorId);
    }
}
