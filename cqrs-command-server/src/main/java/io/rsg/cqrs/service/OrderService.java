package io.rsg.cqrs.service;

import io.rsg.cqrs.dto.OrderResponseDTO;
import io.rsg.cqrs.entity.Order;
import io.rsg.cqrs.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public OrderResponseDTO addOrder(Order order){
        try{
            System.out.println(order.getStatus());
            orderRepository.save(order);
        }catch (Exception e){
            return new OrderResponseDTO(e.getMessage(),false);
        }finally {
            kafkaTemplate.send("order",order);
        }
        return new OrderResponseDTO("Order created.",true);
    }
}
