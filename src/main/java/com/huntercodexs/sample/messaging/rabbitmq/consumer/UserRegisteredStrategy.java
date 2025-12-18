package com.huntercodexs.sample.messaging.rabbitmq.consumer;

import com.huntercodexs.integration.rabbitmq.consumer.RabbitConsumerStrategy;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRegisteredStrategy implements RabbitConsumerStrategy {

    @Override
    public String supports() {
        return "USER_REGISTERED";
    }

    @Override
    public void messageConsumer(String payload, Message originalMessage, Map<String, Object> headers) {
        System.out.println("[Receiving UserRegistered] payload=" + payload);
    }
}
