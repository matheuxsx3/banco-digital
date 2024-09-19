package br.com.microservices.orchestrated.inventoryservice.core.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.properties.topic.orchestrator}")
    private String orchestratorTopic;

    public void sendEvent(String payload) {
        try {
            log.info("Sending event to topic {} with data {}", orchestratorTopic, payload);
            kafkaTemplate.send(orchestratorTopic, payload);
        } catch (Exception e) {
            log.error("Error trying to send data to topic {} with data {}", orchestratorTopic, payload);
        }
    }
} 

    
