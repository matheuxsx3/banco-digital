package br.com.microservices.orchestrated.productvalidationservice.core.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ProductValidationConsumer {

    private final JsonUtils jsonUtils;

    @KafkaListener(topics = "${spring.kafka.properties.topic.product-validation-success}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeProductValidationSuccess(String payload) {
        log.info("Consuming event {} from product-validation-success-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(topics = "${spring.kafka.properties.topic.product-validation-fail}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeProductValidationFailure(String payload) {
        log.info("Consuming rollback event {} from product-validation-fail-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }
}
