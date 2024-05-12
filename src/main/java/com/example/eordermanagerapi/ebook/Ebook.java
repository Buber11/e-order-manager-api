package com.example.eordermanagerapi.ebook;

import com.example.eordermanagerapi.Author.Author;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    
    private String image;

    private String title;

    private Long rating;

    private String tag;

    @ManyToMany(mappedBy = "ebooks")
    private List<Author> authors;


}
