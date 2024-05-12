package com.example.eordermanagerapi.Author.businsessLogic;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;

import java.util.List;

public interface AuthorService {

    List<AuthorDTOView> getAll();
    AuthorDTOView get(long authorId);
}
