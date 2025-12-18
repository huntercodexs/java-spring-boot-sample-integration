package com.huntercodexs.sample.messaging.kafka.producer.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntercodexs.integration.kafka.producer.process.KakfaProducerIntegrationProcess;
import com.huntercodexs.sample.messaging.kafka.dto.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaProducerMessage2Impl implements KakfaProducerIntegrationProcess {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerMessage2Impl.class);

    private static final String CORRELATION_ID_HEADER = "correlationId";

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(String producerName) {
        return producerName.equals("SampleProducer2");
    }

    @Override
    public String processMessage(Object message) {
        try {
            String messageJson = mapper.writeValueAsString((User) message);
            log.info("Processed message to JSON: {}", messageJson);
            return messageJson;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> producerRecord(Object message) {
        User user = (User) message;

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(CORRELATION_ID_HEADER, (MDC.get(CORRELATION_ID_HEADER) != null ? MDC.get(CORRELATION_ID_HEADER): UUID.randomUUID().toString()));
        hashMap.put("name", user.getName());
        hashMap.put("email", user.getEmail());
        hashMap.put("header-key", "SampleHeaderValue2");

        return hashMap;
    }
}
