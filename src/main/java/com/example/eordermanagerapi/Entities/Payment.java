package com.example.eordermanagerapi.Entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_order;
    private String payment_date;
    private String status;
}
