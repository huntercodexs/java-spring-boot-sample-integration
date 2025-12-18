package com.huntercodexs.sample.controlller;

import com.huntercodexs.sample.messaging.rabbitmq.producer.ProducerSample;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitProducerSimulation {

    private final ProducerSample producerSample;

    @PostMapping("/simulate-producer-rabbit/order")
    public void order() {
        System.out.println("Sending Message to RabbitMQ Demo Order...");
        producerSample.demoOrder();
        System.out.println("Message Sent!");
    }

    @PostMapping("/simulate-producer-rabbit/user")
    public void user() {
        System.out.println("Sending Message to RabbitMQ Demo User...");
        producerSample.demoUser();
        System.out.println("Message Sent!");
    }

}
