package com.example.eordermanagerapi.additionalContent;

import com.example.eordermanagerapi.ebook.Ebook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "additional_content")
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_content")
    private Long contentId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ebook")
    @JsonIgnore
    private Ebook ebook;
    @Column(name = "content_url")
    private String contentURL;
}
