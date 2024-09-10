package br.com.microservices.orchestrated.orchestratorservice.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.Etopic;
import lombok.RequiredArgsConstructor;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private static final Integer REPLICA_COUNT = 1;
    private static final Integer PARTITION_COUNT = 1;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return props;
    }

    @Bean
    public Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
        private NewTopic buildTopic(String topicName) {
        return TopicBuilder
                .name(topicName)
                .replicas(REPLICA_COUNT)
                .partitions(PARTITION_COUNT)
                .build();
    }

    @Bean
    public NewTopic startSagaTopic() {
        return buildTopic(Etopic.START_SAGA.getTopic());
    }

    @Bean
    public NewTopic orchestratorTopic() {
        return buildTopic(Etopic.BASE_ORCHESTRATOR.getTopic());
    }
    @Bean
    public NewTopic finishSuccessTopic() {
        return buildTopic(Etopic.FINISH_SUCCESS.getTopic());
    }
    @Bean
    public NewTopic finishFailTopic() {
        return buildTopic(Etopic.FINISH_FAIL.getTopic());
    }
    @Bean
    public NewTopic productValidationSuccessTopic() {
        return buildTopic(Etopic.PRODUCT_VALIDATION_SUCCESS.getTopic());
    }
    @Bean
    public NewTopic productValidationFailTopic() {
        return buildTopic(Etopic.PRODUCT_VALIDATION_FAIL.getTopic());
    }
    @Bean
    public NewTopic paymentSuccessTopic() {
        return buildTopic(Etopic.PAYMENT_SUCCESS.getTopic());
    }
    @Bean
    public NewTopic paymentFailTopic() {
        return buildTopic(Etopic.PAYMENT_FAIL.getTopic());
    }
    @Bean
    public NewTopic inventorySuccessTopic() {
        return buildTopic(Etopic.INVENTORY_SUCCESS.getTopic());
    }
    @Bean
    public NewTopic inventoryFailTopic() {
        return buildTopic(Etopic.INVENTORY_FAIL.getTopic());
    }
    @Bean
    public NewTopic notifyEndingTopic() {
        return buildTopic(Etopic.NOTIFY_ENDING.getTopic());
    }
}
