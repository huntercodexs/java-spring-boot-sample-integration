package com.huntercodexs.sample.messaging.kafka.consumer;

import com.huntercodexs.integration.kafka.consumer.process.KafkaConsumerIntegrationProcess;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class KafkaConsumer2IntegrationProcessImpl implements KafkaConsumerIntegrationProcess {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer2IntegrationProcessImpl.class);

    @Override
    public boolean supports(Headers headers, Object value, Object key, int partition, long offset) {

        log.info("Received message with key: {}, value: {}, partition: {}, offset: {}", key, value, partition, offset);

        Header header = headers.lastHeader("header-key");

        log.info("Checking header 'header-key': {}", header != null ? new String(header.value(), StandardCharsets.UTF_8) : "null");

        if (header == null) {
            return false;
        }
        String value2 = new String(header.value(), StandardCharsets.UTF_8);

        log.info("Header 'header-key' value: {}", value2);

        return "SampleHeaderValue2".equals(value2);
    }

    @Override
    public boolean discard(Headers headers, Object value, Object key, int partition, long offset) {
        log.info("Evaluating discard for message with key: {}, value: {}, partition: {}, offset: {}", key, value, partition, offset);
        return value == null;
    }
}
