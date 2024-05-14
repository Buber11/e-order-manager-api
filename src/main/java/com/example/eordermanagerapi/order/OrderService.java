package com.example.eordermanagerapi.order;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
    List<OrderDtoView> getClientOrders(long clientId);
}
