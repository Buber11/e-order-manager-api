package com.example.eordermanagerapi.order.businessLogic.Command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.order.businessLogic.OrderService;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class AddOrderCommand implements Command<ResponseEntity, OrderService> {

    private OrderRequest request;
    private HttpServletRequest httpServletRequest;

    private AddOrderCommand(OrderRequest request, HttpServletRequest httpServletRequest) {
        this.request = request;
        this.httpServletRequest = httpServletRequest;
    }
    public static AddOrderCommand from(OrderRequest request, HttpServletRequest httpServletRequest){
        return new AddOrderCommand(request,httpServletRequest);
    }

    @Override
    public ResponseEntity execute(OrderService orderService) {
        return orderService.addOrder(request,httpServletRequest);
    }
}
