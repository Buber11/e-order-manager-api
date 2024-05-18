package com.example.eordermanagerapi.order.Command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.Order;
import com.example.eordermanagerapi.order.OrderService;

import java.util.List;
//zmien order na orderDTO
public class GetClientOrdersCommand implements Command<List<OrderDtoView>, OrderService> {

    private final long clientId;


    private GetClientOrdersCommand(long clientId) {
        this.clientId = clientId;
    }

    public static GetClientOrdersCommand from(long orderId){
        return new GetClientOrdersCommand(orderId);
    }
    @Override
    public List<OrderDtoView> execute(OrderService orderService) {
        return orderService.getClientOrders(clientId);
    }

}