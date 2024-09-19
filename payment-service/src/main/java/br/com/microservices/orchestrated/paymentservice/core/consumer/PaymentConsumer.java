package br.com.microservices.orchestrated.paymentservice.core.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.microservices.orchestrated.paymentservice.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentConsumer {

    private final JsonUtils jsonUtils;

    @KafkaListener(topics = "${spring.kafka.properties.topic.payment-success}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentSuccess(String payload) {
        log.info("Consuming event {} from payment-success-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(topics = "${spring.kafka.properties.topic.payment-fail}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumePaymentFailure(String payload) {
        log.info("Consuming rollback event {} from payment-fail-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }
}
