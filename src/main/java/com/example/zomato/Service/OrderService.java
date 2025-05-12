package com.example.zomato.Service;

import com.example.zomato.Entity.OrderEntity;
import com.example.zomato.Entity.OrderItemEntity;
import com.example.zomato.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity saveOrder(OrderEntity order) {
        for (OrderItemEntity item : order.getOrderItems()) {
            item.setOrder(order); // Set the parent reference for cascading
        }
        return orderRepository.save(order);  // Save the order along with its items
    }
}
