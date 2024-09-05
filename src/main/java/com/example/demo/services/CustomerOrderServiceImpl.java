package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository orderRepository;

    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CustomerOrder createOrder(Long id, String customerEmail, String customerAddress, Date orderDate) {
        CustomerOrder order = new CustomerOrder(id, customerEmail, customerAddress, orderDate);
        return orderRepository.save(order);
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        Optional<CustomerOrder> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public void updateDeliveryStatus(Long id, String status) {
        Optional<CustomerOrder> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            CustomerOrder order = orderOpt.get();
            order.updateDeliveryStatus(status);
            orderRepository.save(order);
        }
    }

    @Override
    public void sendOrderForDelivery(Long id) {
        Optional<CustomerOrder> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            CustomerOrder order = orderOpt.get();
            order.sendForDelivery();
            orderRepository.save(order);
        }
    }
}
