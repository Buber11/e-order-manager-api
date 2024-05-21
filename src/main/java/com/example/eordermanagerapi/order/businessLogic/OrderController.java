package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import com.example.eordermanagerapi.order.businessLogic.Command.AddOrderCommand;
import com.example.eordermanagerapi.order.businessLogic.Command.GetClientOrdersCommand;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Fasada fasada;

    public OrderController(Fasada fasada) {
        this.fasada = fasada;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest request, HttpServletRequest httpServletRequest) {
        try {
            fasada.handle(AddOrderCommand.from(request,httpServletRequest));
            return buildSuccessResponse("Order created successfully");
        } catch (DataAccessException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Error occurred while saving Order: " + e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/client/{clientId}/orders")
    public ResponseEntity<?> getClientOrders(@PathVariable long clientId) {
        List<OrderDtoView> orders = fasada.handle(GetClientOrdersCommand.from(clientId));
        if (orders.isEmpty()) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "No orders found for this client");
        }
        return buildSuccessResponse(orders);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }

    private <T> ResponseEntity<T> buildSuccessResponse(T body) {
        return ResponseEntity.ok(body);
    }

    private ResponseEntity<Map<String, String>> buildSuccessResponse(String message) {
        return ResponseEntity.ok(Map.of("message", message));
    }
}
