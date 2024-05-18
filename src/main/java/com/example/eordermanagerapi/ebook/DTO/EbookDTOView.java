package com.example.eordermanagerapi.ebook.DTO;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import lombok.*;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EbookDTOView {
    private Long ebookId;
    private String image;
    private String title;
    private Long rating;
    private String tag;
    private List<AuthorDTOForEbook> authors;
}
