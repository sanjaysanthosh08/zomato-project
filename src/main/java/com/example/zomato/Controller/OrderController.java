package com.example.zomato.Controller;

import com.example.zomato.Entity.OrderEntity;
import com.example.zomato.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")  // Ensure CORS is enabled
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderEntity placeOrder(@RequestBody OrderEntity order) {
        return orderService.saveOrder(order);  // This will save the order and its items
    }
}
