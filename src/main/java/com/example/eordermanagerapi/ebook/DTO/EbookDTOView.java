package com.example.eordermanagerapi.ebook.DTO;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import com.example.eordermanagerapi.additionalContent.AdditionalContent;
import com.example.eordermanagerapi.additionalContent.AdditionalContentDTOView;
import lombok.*;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EbookDTOView {
    private Long id;
    private String image;
    private String title;
    private Long rating;
    private String tag;
    private List<AuthorDTOForEbook> authors;
    private List<AdditionalContentDTOView> additionalContents;
    private String mainContent;
    private Double price;
}
