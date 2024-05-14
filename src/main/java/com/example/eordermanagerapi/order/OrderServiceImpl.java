package com.example.eordermanagerapi.order;

import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDtoView> getClientOrders(long clientId){
        List<Order> allByClientId = orderRepository.findAllByClientId(clientId);
        return allByClientId.stream()
                .map(order -> OrderDtoView.builder()
                        .orderId(order.getOrderId())
                        .price(order.getPrice())
                        .purchaseDate(order.getPurchaseDate())
                        .status(order.getStatus()).build()
                        ).collect(Collectors.toList());

    }

}
