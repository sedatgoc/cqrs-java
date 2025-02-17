package io.rsg.cqrs.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.rsg.cqrs.entity.Order;
import io.rsg.cqrs.repository.OrderRepository;
import jakarta.json.Json;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderConsumerImpl implements KafkaConsumer {

    @Autowired
    OrderRepository orderEsRepository;

    @Override
    @KafkaListener(topics = "order", groupId = "group")
    public void listenTopic(ConsumerRecord<String, String> message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        System.out.println(message.value());
        Order order = objectMapper.readValue(message.value(), Order.class);
        orderEsRepository.save(order);
    }
}
