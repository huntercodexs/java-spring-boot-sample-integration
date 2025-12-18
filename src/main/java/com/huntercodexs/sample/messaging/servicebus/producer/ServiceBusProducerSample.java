package com.huntercodexs.sample.messaging.servicebus.producer;

import com.huntercodexs.integration.servicebus.producer.implement.ServiceBusIntegrationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceBusProducerSample {

    private final ServiceBusIntegrationProducer serviceBusIntegrationProducer;

    public void sendMessageSample(String message) {
        serviceBusIntegrationProducer.send(null, 0, message, String.class);
    }

}
