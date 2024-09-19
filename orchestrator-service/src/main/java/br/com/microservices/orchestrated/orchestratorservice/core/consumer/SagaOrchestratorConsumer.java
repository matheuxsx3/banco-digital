package br.com.microservices.orchestrated.orchestratorservice.core.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.microservices.orchestrated.orchestratorservice.core.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class SagaOrchestratorConsumer {

    private final JsonUtils jsonUtils;

    @KafkaListener(topics = "${spring.kafka.properties.topic.start-saga}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeStartSaga(String payload) {
        log.info("Consuming event {} from start-saga-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(topics = "${spring.kafka.properties.topic.orchestrator}", groupId = "${spring.kafka.consumer.group-id}")
    
    public void consumeOrchestrator(String payload) {
        log.info("Consuming event {} from orchestrator-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(topics = "${spring.kafka.properties.topic.finish-success}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeFinishSuccess(String payload) {
        log.info("Consuming event {} from finish-success-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(topics = "${spring.kafka.properties.topic.finish-fail}", groupId = "${spring.kafka.consumer.group-id}")

    public void consumeFinishFail(String payload) {
        log.info("Consuming event {} from finish-fail-topic", payload);
        var event = jsonUtils.toEvent(payload);
        log.info(event.toString());
    }

}
