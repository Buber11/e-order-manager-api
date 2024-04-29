package com.example.eordermanagerapi.Entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_order;
    private String purchase_date;
    private double price;
    private String status;
}
