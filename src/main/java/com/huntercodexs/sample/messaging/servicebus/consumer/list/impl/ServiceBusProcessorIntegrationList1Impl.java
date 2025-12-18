package com.huntercodexs.sample.messaging.servicebus.consumer.list.impl;

import com.huntercodexs.integration.servicebus.consumer.implement.ServiceBusProcessorIntegration;
import com.huntercodexs.integration.servicebus.context.ServiceBusErrorContextIntegration;
import com.huntercodexs.integration.servicebus.context.ServiceBusMessageContextIntegration;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceBusProcessorIntegrationList1Impl implements ServiceBusProcessorIntegration {

    private static final Logger log = LoggerFactory.getLogger(ServiceBusProcessorIntegrationList1Impl.class);

    @Override
    public boolean supports(String queueName) {
        return queueName.equals("list1-queue-sample");
    }

    @Override
    public void processMessage(ServiceBusMessageContextIntegration mensagem) {
        log.info("Processing list1 message {}", mensagem);
        mensagem.getActions().abandon();
    }

    @Override
    public void processError(ServiceBusErrorContextIntegration context) {
        log.error("Error occurred in Service Bus processing: {}", context.getException().getMessage());
    }

}
