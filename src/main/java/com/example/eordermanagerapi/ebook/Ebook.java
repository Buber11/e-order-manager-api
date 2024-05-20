package com.example.eordermanagerapi.ebook;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.additionalContent.AdditionalContent;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ebook")
    private Long ebookId;
    
    private String image;

    private String title;

    private Long rating;

    private String tag;

    @OneToMany(mappedBy = "ebook",cascade = CascadeType.ALL)
    private List<AdditionalContent> additionalContent;

    @Column(name = "main_content")
    private String mainContent;

    @ManyToMany(mappedBy = "ebooks")
    private List<Author> authors;

    private Double price;


}
