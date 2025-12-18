package com.huntercodexs.sample.controlller;

import com.huntercodexs.sample.messaging.servicebus.producer.ServiceBusProducerSample;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServiceBusProducerSimulation {

    private final ServiceBusProducerSample serviceBusProducerSample;

    @PostMapping("/simulate-producer-servicebus")
    public void simulateProducerServiceBus() {
        System.out.println("Simulating Service Bus Producer Message Sending...");
        serviceBusProducerSample.sendMessageSample("{\"test\":9}");
        System.out.println("Message Sent!");
    }

}
