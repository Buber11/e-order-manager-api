package com.example.eordermanagerapi.order.businessLogic.Command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.businessLogic.OrderService;
import org.springframework.http.ResponseEntity;

import java.util.List;
//zmien order na orderDTO
public class GetClientOrdersCommand implements Command<ResponseEntity, OrderService> {

    private final long clientId;


    private GetClientOrdersCommand(long clientId) {
        this.clientId = clientId;
    }

    public static GetClientOrdersCommand from(long orderId){
        return new GetClientOrdersCommand(orderId);
    }
    @Override
    public ResponseEntity execute(OrderService orderService) {
        return orderService.getClientOrders(clientId);
    }

}
