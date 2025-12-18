package com.huntercodexs.sample.messaging.sqs.consumer;

import com.huntercodexs.integration.sqs.consumer.SqsCustomHeadersIntegration;
import com.huntercodexs.integration.sqs.consumer.implement.SqsConsumerIntegration;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer2Sample implements SqsConsumerIntegration {

    @Override
    public boolean supports(String queueName) {
        return queueName.equalsIgnoreCase("sqs-queue-test2");
    }

    @Override
    public void consumer(String payload, SqsCustomHeadersIntegration headers) {
        System.out.println("Consumed message from sqs-queue-test2: " + payload);
        System.out.println("Headers 2: " + headers);
    }

}
