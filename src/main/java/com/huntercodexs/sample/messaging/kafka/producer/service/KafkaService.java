package com.huntercodexs.sample.messaging.kafka.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huntercodexs.integration.kafka.producer.sender.KafkaProducerIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.huntercodexs.integration.kafka.producer.constants.KafkaProducerIntegrationConstants.TOPIC_DEFAULT;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaProducerIntegration kafkaProducer;

    public void sendMessage1(Object message) throws JsonProcessingException {
        kafkaProducer.send(message, "SampleProducer1", TOPIC_DEFAULT);
    }

    public void sendMessage2(Object message) throws JsonProcessingException {
        kafkaProducer.send(message, "SampleProducer2", TOPIC_DEFAULT);
    }
}
