package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    List<AuthorDTOView> getAllAuthors();
    AuthorDTOView getAuthorById(long authorId);
}
