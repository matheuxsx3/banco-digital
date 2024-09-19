package br.com.microservices.orchestrated.inventoryservice.core.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.microservices.orchestrated.inventoryservice.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryConsumer {

    private final JsonUtils jsonUtils;

    @KafkaListener(topics = "${spring.kafka.properties.topic.inventory-success}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeInventorySuccess(String payload) {
        log.info("Consuming event {} from inventory-success-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(topics = "${spring.kafka.properties.topic.inventory-fail}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeInventoryFailure(String payload) {
        log.info("Consuming rollback event {} from inventory-fail-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }
}
