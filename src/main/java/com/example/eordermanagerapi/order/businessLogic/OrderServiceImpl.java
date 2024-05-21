package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.Client.ClientRepository;
import com.example.eordermanagerapi.order.*;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderNeo4jRepository orderNeo4jRepository;
    private final ClientRepository clientRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderNeo4jRepository orderNeo4jRepository,
                            ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.orderNeo4jRepository = orderNeo4jRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void addOrder(OrderRequest request, HttpServletRequest httpServletRequest) throws DataAccessException {
        long userId = (long) httpServletRequest.getAttribute("id");
        long clientId = clientRepository.getClinetIdByUserId(userId);

        Order order = Order.builder()
                .status(OrderStatus.IN_PROGRESS)
                .price(request.price())
                .clientId(clientId)
                .build();

        Order createdOrder = orderRepository.save(order);
        long orderId = createdOrder.getOrderId();

        if (orderId > 0) {
            OrderNeo4j orderNeo4j = OrderNeo4j.builder()
                    .orderId(orderId)
                    .status(OrderStatus.IN_PROGRESS.name())
                    .price(request.price())
                    .clientId(clientId)
                    .build();
            orderNeo4jRepository.save(orderNeo4j);
            System.out.println("Order and OrderNeo4j saved successfully with orderId: " + orderId);
        } else {
            System.err.println("Failed to save Order.");
            throw new DataAccessException("Failed to save Order.") {};
        }
    }

    @Override
    public List<OrderDtoView> getClientOrders(long clientId) {
        return orderRepository.findAllByClientId(clientId).stream()
                .map(order -> OrderDtoView.builder()
                        .orderId(order.getOrderId())
                        .price(order.getPrice())
                        .purchaseDate(order.getPurchaseDate())
                        .status(order.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}