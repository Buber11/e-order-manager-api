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
    public ResponseEntity<?> addOrder(OrderRequest request, HttpServletRequest httpServletRequest) {
        long userId = (long) httpServletRequest.getAttribute("id");

        long clientId = clientRepository.getClinetIdByUserId(userId);

        Order order = Order.builder()
                .status(OrderStatus.IN_PROGRESS)
                .price(request.price())
                .clientId(clientId)
                .build();
        try {
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

                return buildSuccessResponse("Order created successfully");
            } else {
                System.err.println("Failed to save Order.");
                return buildErrorResponse(HttpStatus.BAD_REQUEST, "Failed to save Order.");
            }
        } catch (DataAccessException e) {
            System.err.println("Error occurred while saving Order: " + e.getMessage());
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Error occurred while saving Order: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getClientOrders(long clientId) {
        List<Order> allByClientId = orderRepository.findAllByClientId(clientId);

        if (allByClientId.isEmpty()) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "No orders found for this client");
        }

        List<OrderDtoView> orderDtoViews = allByClientId.stream()
                .map(order -> OrderDtoView.builder()
                        .orderId(order.getOrderId())
                        .price(order.getPrice())
                        .purchaseDate(order.getPurchaseDate())
                        .status(order.getStatus())
                        .build())
                .collect(Collectors.toList());

        return buildSuccessResponse(orderDtoViews);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private <T> ResponseEntity<T> buildSuccessResponse(T t) {
        return ResponseEntity.ok(t);
    }

    private ResponseEntity<Map<String, String>> buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message", message));
    }

}
