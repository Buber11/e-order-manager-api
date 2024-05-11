package com.example.eordermanagerapi.ebook.DTO;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EbookDTOView {
    private Long ebookId;
    private String imagineUrl;
    private String title;
    private Long rating;
    private String tag;
    private List<AuthorDTOForEbook> authors;
}
