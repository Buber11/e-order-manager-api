package com.example.eordermanagerapi.Author.DTO;

import com.example.eordermanagerapi.ebook.DTO.EbookDTOForAuthor;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import lombok.*;

import java.util.Date;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class AuthorDTOView {
    private long Id;
    private Date signUpDate;
    private String name;
    private String surname;
    private String email;
    private List<EbookDTOForAuthor> ebooks;

}
