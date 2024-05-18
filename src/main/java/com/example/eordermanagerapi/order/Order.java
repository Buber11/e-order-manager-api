package com.example.eordermanagerapi.order;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long orderId;
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "purchase_date")
    @CreationTimestamp
    private Date purchaseDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
