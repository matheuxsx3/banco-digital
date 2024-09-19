package br.com.microservices.orchestrated.orderservice.core.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EventConsumer {

    private final JsonUtils jsonUtils;

    @KafkaListener(topics = "${spring.kafka.properties.topic.notify-ending}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeNotifyEnding(String payload) {
        log.info("Consuming end notify event {} from notify-ending-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

}
