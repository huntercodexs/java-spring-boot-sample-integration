package com.huntercodexs.sample.controlller;

import com.huntercodexs.sample.messaging.sqs.producer.SqsProducerSample;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SqsProducerSimulation {

    private final SqsProducerSample sqsProducerSample;

    @PostMapping("/simulate-producer-sqs/publish1")
    public void publish1() {
        System.out.println("Sending Message to SQS Demo...1");
        sqsProducerSample.publisher1("testing message 1");
        System.out.println("Message Sent 1");
    }

    @PostMapping("/simulate-producer-sqs/publish1dlq")
    public void publish1dlq() {
        System.out.println("Sending Message to SQS Demo...1DLQ");
        sqsProducerSample.publisher1("error message to trigger DLQ");
        System.out.println("Message Sent 1DLQ");
    }

    @PostMapping("/simulate-producer-sqs/publish2")
    public void publish2() {
        System.out.println("Sending Message to SQS Demo...2");
        sqsProducerSample.publisher2("testing message 2");
        System.out.println("Message Sent 2");
    }

}
