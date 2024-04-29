package com.example.eordermanagerapi.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_ebook")
public class OrderEbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long order_id;
    private long ebook_id;
    private double ebook_price;
}
