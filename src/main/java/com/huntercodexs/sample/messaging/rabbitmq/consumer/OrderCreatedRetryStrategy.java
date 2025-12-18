package com.huntercodexs.sample.messaging.rabbitmq.consumer;

import com.huntercodexs.integration.rabbitmq.consumer.RabbitConsumerStrategy;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderCreatedRetryStrategy implements RabbitConsumerStrategy {

    @Override
    public String supports() {
        return "ORDER_CREATED_RETRY";
    }

    @Override
    public void messageConsumer(String payload, Message originalMessage, Map<String, Object> headers) throws Exception {
        // maybe different logic on retries, for demo use same
        System.out.println("[Receiving OrderCreatedRetry] payload=" + payload + " headers=" + headers);
    }
}
