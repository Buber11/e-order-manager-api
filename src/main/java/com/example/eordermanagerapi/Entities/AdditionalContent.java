package com.example.eordermanagerapi.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "additional_content")
@Entity
public class AdditionalContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content_URL;
}
