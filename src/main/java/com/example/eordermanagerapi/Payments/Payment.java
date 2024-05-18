package com.example.eordermanagerapi.Payments;

import com.example.eordermanagerapi.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    long paymentId;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id_order")
    private Order order;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}

