package io.rsg.cqrs.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumer {
    void listenTopic(ConsumerRecord<String, String> message) throws JsonProcessingException;
}
