package com.example.eordermanagerapi.payload.request;

import com.example.eordermanagerapi.order.OrderStatus;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @Positive(message = "price have to be positive")
        Double price
){
}
