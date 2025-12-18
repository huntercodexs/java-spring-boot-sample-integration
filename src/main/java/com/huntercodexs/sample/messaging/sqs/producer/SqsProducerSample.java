package com.huntercodexs.sample.messaging.sqs.producer;

import com.huntercodexs.integration.sqs.producer.SqsProducerIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SqsProducerSample {

    @Autowired
    private final SqsProducerIntegration sqsProducerIntegration;

    public void publisher1(String message) {
        sqsProducerIntegration.send(message, "sqs-queue-test1");
    }

    public void publisher2(String message) {
        sqsProducerIntegration.send(message, "sqs-queue-test2");
    }

}
