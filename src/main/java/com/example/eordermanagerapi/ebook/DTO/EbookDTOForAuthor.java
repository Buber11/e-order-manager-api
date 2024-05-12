package com.example.eordermanagerapi.ebook.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EbookDTOForAuthor {
    private Long ebookId;
    private String image;
    private String title;
    private Long rating;
    private String tag;
}
