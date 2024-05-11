package com.example.eordermanagerapi.Author.DTO;

import lombok.*;

import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class AuthorDTOForEbook {
    private long authorId;
    private Date signUpDate;
    private String name;
    private String surname;
    private String email;
}
