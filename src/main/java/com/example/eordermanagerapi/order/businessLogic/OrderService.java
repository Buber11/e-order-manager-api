package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.Order;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    ResponseEntity addOrder(OrderRequest request, HttpServletRequest httpServletRequest);

    ResponseEntity getClientOrders(long clientId);
}
