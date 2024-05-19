package com.example.eordermanagerapi.ebook.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EbookDTOForAuthor {
    private Long Id;
    private String image;
    private String title;
    private Long rating;
    private String tag;
}
