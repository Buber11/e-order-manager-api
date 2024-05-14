package com.example.eordermanagerapi.Payments;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    long paymentId;
    @Column(name = "order_id")
    int orderId;
    @Column(name = "payment_date")
    Date paymentDate;

    String status;

}
