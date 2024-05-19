package com.example.eordermanagerapi.order.businessLogic;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.order.businessLogic.Command.AddOrderCommand;
import com.example.eordermanagerapi.order.businessLogic.Command.GetClientOrdersCommand;
import com.example.eordermanagerapi.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final Fasada fasada;
    public OrderController(Fasada fasada) {
        this.fasada = fasada;
    }
    @GetMapping("/get")
    public ResponseEntity get(@RequestParam(name = "id")long clientId){
        var orders = fasada.handle(GetClientOrdersCommand.from(clientId));
        if(orders != null){
            return ResponseEntity.ok(orders);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody OrderRequest request,
                              HttpServletRequest httpServletRequest,
                              BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        ModelAndView modelAndView = fasada.handle(AddOrderCommand.from(request,httpServletRequest));
        return ResponseEntity.status(modelAndView.getStatus()).body(modelAndView.getModel().get("error"));

    }
}
