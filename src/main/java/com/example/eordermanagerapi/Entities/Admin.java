package com.example.eordermanagerapi.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "admin")
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long user_id;
    private String signup_date;
}
