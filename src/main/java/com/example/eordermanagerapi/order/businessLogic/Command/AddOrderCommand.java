package com.example.eordermanagerapi.order.businessLogic.Command;

import com.example.eordermanagerapi.Fasada.Command;
import com.example.eordermanagerapi.order.businessLogic.OrderService;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public class AddOrderCommand implements Command<Void, OrderService> {

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
    public Void execute(OrderService orderService) {
        orderService.addOrder(request,httpServletRequest);
        return null;
    }
}
