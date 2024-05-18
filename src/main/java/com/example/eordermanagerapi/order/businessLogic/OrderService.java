package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.Order;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    ModelAndView addOrder(OrderRequest request, HttpServletRequest httpServletRequest);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
    List<OrderDtoView> getClientOrders(long clientId);
}
