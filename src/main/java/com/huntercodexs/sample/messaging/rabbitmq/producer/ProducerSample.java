package com.huntercodexs.sample.messaging.rabbitmq.producer;

import com.huntercodexs.integration.rabbitmq.producer.RabbitProducerIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerSample {

    private final RabbitProducerIntegration producer;

    public void demoOrder() {
        producer.send("ORDER_CREATED", "{\"orderId\":\"1\",\"product\":\"book\",\"quantity\":1}");
        producer.send("ORDER_CREATED", "{\"orderId\":\"2\",\"error\":\"dlq\"}");
        producer.send("ORDER_CREATED", "{\"orderId\":\"3\",\"error\":\"retry\"}");
        producer.send("ORDER_CREATED", "{\"orderId\":\"4\",\"error\":\"router\"}");
    }

    public void demoUser() {
        producer.send("USER_REGISTERED", "{\"userId\":\"u-1\",\"name\":\"Ana\",\"email\":\"ana@example.com\"}");
    }
}
