package com.example.eordermanagerapi.Author.DTO;

import lombok.*;

import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTOForEbook {
    private long id;
    private Date signUpDate;
    private String name;
    private String surname;
    private String email;
}
