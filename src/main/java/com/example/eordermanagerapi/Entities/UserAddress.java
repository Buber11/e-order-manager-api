package com.example.eordermanagerapi.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_address")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long user_id;
    private long address_id;
}
