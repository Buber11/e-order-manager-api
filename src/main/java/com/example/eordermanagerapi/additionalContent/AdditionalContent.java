package com.example.eordermanagerapi.additionalContent;

import com.example.eordermanagerapi.ebook.Ebook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an additional content entity associated with an Ebook.
 */
@Data
@Builder
@Entity
@Table(name = "additional_content")
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalContent {

    /** The unique identifier for the additional content. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_content")
    private Long contentId;

    /** The Ebook associated with this additional content. */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ebook")
    @JsonIgnore
    private Ebook ebook;

    /** The URL of the additional content. */
    @Column(name = "content_url")
    private String contentURL;
}
