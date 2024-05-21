package com.example.eordermanagerapi.ebook;

import com.example.eordermanagerapi.Author.Author;
import com.example.eordermanagerapi.additionalContent.AdditionalContent;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity class representing an Ebook.
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ebook {
    /**
     * Unique identifier for the ebook.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ebook")
    private Long ebookId;

    /**
     * Image URL of the ebook.
     */
    private String image;

    /**
     * Title of the ebook.
     */
    private String title;

    /**
     * Rating of the ebook.
     */
    private Long rating;

    /**
     * Tag of the ebook.
     */
    private String tag;

    /**
     * List of additional content related to the ebook.
     */
    @OneToMany(mappedBy = "ebook",cascade = CascadeType.ALL)
    private List<AdditionalContent> additionalContent;

    /**
     * Main content of the ebook.
     */
    @Column(name = "main_content")
    private String mainContent;

    /**
     * List of authors who contributed to the ebook.
     */
    @ManyToMany(mappedBy = "ebooks")
    private List<Author> authors;

    /**
     * Price of the ebook.
     */
    private Double price;
}