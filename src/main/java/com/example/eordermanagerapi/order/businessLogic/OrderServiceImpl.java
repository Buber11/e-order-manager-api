package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.Client.ClientRepository;
import com.example.eordermanagerapi.order.*;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
    public ModelAndView addOrder(OrderRequest request, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
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
            } else {
                System.err.println("Failed to save Order.");
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
                modelAndView.addObject("error","Failed to save Order.");
            }
        } catch (DataAccessException e) {
            System.err.println("Error occurred while saving Order: " + e.getMessage());
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            modelAndView.addObject("error","Error occurred while saving Order: " + e.getMessage());
        }
        modelAndView.setStatus(HttpStatusCode.valueOf(200));
        return modelAndView;
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
