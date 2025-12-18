package com.huntercodexs.sample.messaging.servicebus.consumer.single.impl;

import com.huntercodexs.integration.servicebus.consumer.implement.ServiceBusProcessorIntegration;
import com.huntercodexs.integration.servicebus.context.ServiceBusErrorContextIntegration;
import com.huntercodexs.integration.servicebus.context.ServiceBusMessageContextIntegration;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceBusProcessorIntegrationSingleImpl implements ServiceBusProcessorIntegration {

    private static final Logger log = LoggerFactory.getLogger(ServiceBusProcessorIntegrationSingleImpl.class);

    @Override
    public boolean supports(String queueName) {
        return queueName.equals("first-queue-for-tests");
    }

    @Override
    public void processMessage(ServiceBusMessageContextIntegration message) {
        log.info("Processing queue: first-queue-for-tests message: {}", message.toString());
        log.info("Data-Processing queue: first-queue-for-tests message: {}", message.getData().toString());
        log.info("Details-Processing queue: first-queue-for-tests message: {}", message.getDetails().toString());
        log.info("Actions-Processing queue: first-queue-for-tests message: {}", message.getActions().toString());
        message.getActions().abandon();
    }

    @Override
    public void processError(ServiceBusErrorContextIntegration context) {
        log.error("Error occurred in Service Bus processing: {}", context.getException().getMessage());
    }

}
