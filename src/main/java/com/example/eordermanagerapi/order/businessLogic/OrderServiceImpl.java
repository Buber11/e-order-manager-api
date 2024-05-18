package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.Order;
import com.example.eordermanagerapi.order.OrderNeo4jRepository;
import com.example.eordermanagerapi.order.OrderRepository;
import com.example.eordermanagerapi.order.businessLogic.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderNeo4jRepository orderNeo4jRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderNeo4jRepository orderNeo4jRepository) {
        this.orderRepository = orderRepository;
        this.orderNeo4jRepository = orderNeo4jRepository;
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
    public List getClientOrders(long clientId){
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
