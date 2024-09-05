package com.example.demo.controllers;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        return orderService.createOrder(order.getId(), order.getCustomerEmail(), order.getCustomerAddress(), new Date());
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}/status")
    public void updateDeliveryStatus(@PathVariable Long id, @RequestBody String status) {
        orderService.updateDeliveryStatus(id, status);
    }

    @PutMapping("/{id}/sendDelivery")
    public void sendOrderForDelivery(@PathVariable Long id) {
        orderService.sendOrderForDelivery(id);
    }
}
