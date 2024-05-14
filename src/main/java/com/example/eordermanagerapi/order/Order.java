package com.example.eordermanagerapi.order;

import com.example.eordermanagerapi.order.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long orderId;
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private OrderStatus status;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    //manytoOneClien zamiast clientID
}
