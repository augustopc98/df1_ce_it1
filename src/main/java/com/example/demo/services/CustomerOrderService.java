package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;

import java.util.Date;

public interface CustomerOrderService {
    CustomerOrder createOrder(Long id, String customerEmail, String customerAddress, Date orderDate);
    CustomerOrder getOrderById(Long id);
    void updateDeliveryStatus(Long id, String status);
    void sendOrderForDelivery(Long id);
}
