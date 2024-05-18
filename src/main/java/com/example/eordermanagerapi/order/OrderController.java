package com.example.eordermanagerapi.order;

import com.example.eordermanagerapi.Fasada.Fasada;
import com.example.eordermanagerapi.order.Command.GetClientOrdersCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
