package com.example.eordermanagerapi.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ebook")
@Data
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String image_url;
    private String title;
    private String author;
    private String rating;
    private String tag;
}
