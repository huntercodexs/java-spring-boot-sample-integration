package com.huntercodexs.sample.messaging.sqs.consumer;

import com.huntercodexs.integration.sqs.consumer.SqsCustomHeadersIntegration;
import com.huntercodexs.integration.sqs.consumer.implement.SqsConsumerIntegration;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer1Sample implements SqsConsumerIntegration {

    @Override
    public boolean supports(String queueName) {
        return queueName.equalsIgnoreCase("sqs-queue-test1");
    }

    @Override
    public void consumer(String payload, SqsCustomHeadersIntegration headers) {
        System.out.println("Consumed message from sqs-queue-test1: " + payload);
        System.out.println("Headers 1: " + headers);

        if (payload.contains("error")) {
            throw new RuntimeException("Simulated processing error in SqsConsumer1Sample");
        }
    }

}
