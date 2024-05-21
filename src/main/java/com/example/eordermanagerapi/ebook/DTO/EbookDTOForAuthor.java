package com.example.eordermanagerapi.ebook.DTO;

import lombok.*;

/**
 * Data transfer object (DTO) for representing ebook details in the context of an author.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EbookDTOForAuthor {
    private Long id;
    private String image;
    private String title;
    private Long rating;
    private String tag;
}