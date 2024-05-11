package com.example.eordermanagerapi.ebook;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ebook")
    private Long ebookId;
    @Column(name = "image_url")
    private String imagineUrl;
    private String title;
    private String author;
    private Long rating;
    private String tag;

}
