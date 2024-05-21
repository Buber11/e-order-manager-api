package com.example.eordermanagerapi.ebook.DTO;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOForEbook;
import com.example.eordermanagerapi.additionalContent.AdditionalContent;
import com.example.eordermanagerapi.additionalContent.AdditionalContentDTOView;
import lombok.*;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;

import java.io.Serializable;
import java.util.List;
/**
 * Data transfer object (DTO) for representing ebook details.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EbookDTOView implements Serializable {
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