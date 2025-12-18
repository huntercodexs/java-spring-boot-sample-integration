package com.huntercodexs.sample.messaging.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntercodexs.sample.messaging.kafka.dto.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

import static com.huntercodexs.integration.kafka.consumer.constants.KafkaConsumerIntegrationConstants.GROUP_ID_DEFAULT;
import static com.huntercodexs.integration.kafka.consumer.constants.KafkaConsumerIntegrationConstants.TOPIC_DEFAULT;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = TOPIC_DEFAULT, groupId = GROUP_ID_DEFAULT, containerFactory = "kafkaListenerContainerFactory")
    public void messageConsume(@Payload String event, @Headers Map<String, Object> headers, Acknowledgment ack) throws JsonProcessingException {

        final var correlation = UUID.randomUUID().toString();

        MDC.put("correlationId", correlation);

        log.info("Message received from topic: {} | event: {}", headers, event);

        String value = headerValue(headers);

        log.info(">>> Header 'header-key' value: {}", value);

        if (value == null || value.equals("SampleHeaderValue1")) {
            log.info("Processing message 1");
        } else if (value.equals("SampleHeaderValue2")) {
            log.info("Processing message 2");
        } else {
            log.info("Unknown header value, skipping processing");
            ack.acknowledge();
            return;
        }

        final var userData = objectMapper.readValue(event, User.class);

        log.info("Data mapped to User: {}", userData);

        ack.acknowledge();
    }

    private String headerValue(Map<String, Object> headers) {
        Object headerVal = headers.get("header-key");
        String headerStr = null;
        if (headerVal instanceof byte[]) {
            headerStr = new String((byte[]) headerVal, java.nio.charset.StandardCharsets.UTF_8);
        } else if (headerVal != null) {
            headerStr = headerVal.toString();
        }

        return headerStr;
    }
}
