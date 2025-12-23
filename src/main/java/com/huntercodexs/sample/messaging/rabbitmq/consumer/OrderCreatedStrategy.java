package com.huntercodexs.sample.messaging.rabbitmq.consumer;

import com.huntercodexs.integration.rabbitmq.consumer.RabbitConsumerStrategy;
import com.huntercodexs.integration.rabbitmq.core.handler.RabbitExceptionDlqIntegration;
import com.huntercodexs.integration.rabbitmq.core.handler.RabbitExceptionRetryIntegration;
import com.huntercodexs.integration.rabbitmq.core.handler.RabbitExceptionRouterIntegration;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderCreatedStrategy implements RabbitConsumerStrategy {

    @Override
    public String supports() {
        return "ORDER_CREATED";
    }

    @Override
    public void messageConsumer(String payload, Message originalMessage, Map<String, Object> headers) throws RabbitExceptionRetryIntegration {
        // parse payload if JSON, for demo do simple print
        System.out.println("[Receiving OrderCreated] payload=" + payload);
        // simulate processing...
        if (payload.contains("dlq")) {
            throw new RabbitExceptionDlqIntegration("Simulated processing error");
        }
        if (payload.contains("retry")) {
            throw new RabbitExceptionRetryIntegration("Simulated processing error");
        }
        if (payload.contains("router")) {
            throw new RabbitExceptionRouterIntegration("", "ORDER_CREATED_RETRY");
        }
    }
}
