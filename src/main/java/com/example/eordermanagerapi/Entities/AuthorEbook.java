package com.example.eordermanagerapi.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "author_ebook")
public class AuthorEbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long author_id;
    private long ebook_id;
}
