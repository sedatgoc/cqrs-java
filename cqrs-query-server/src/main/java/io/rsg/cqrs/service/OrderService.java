package io.rsg.cqrs.service;

import io.rsg.cqrs.entity.Order;
import io.rsg.cqrs.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Optional<Order> getOrder(String orderId){
        return orderRepository.findById(orderId);
    }
}
