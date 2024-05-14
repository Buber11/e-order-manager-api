package com.example.eordermanagerapi.order.DTO;

import com.example.eordermanagerapi.order.Enum.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class OrderDtoView {
    private long orderId;
    
    private Date purchaseDate;
    private Double price;
    private OrderStatus status;
}
