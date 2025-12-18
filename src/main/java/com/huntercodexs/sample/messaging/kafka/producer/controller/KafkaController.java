package com.huntercodexs.sample.messaging.kafka.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huntercodexs.sample.messaging.kafka.dto.User;
import com.huntercodexs.sample.messaging.kafka.producer.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping("/send/1")
    public ResponseEntity<String> sendMessage1(@RequestBody User user) {
        try {
            kafkaService.sendMessage1(user);
            return ResponseEntity.ok("Message sent successfully - 1");
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Message error process: " + e.getMessage());
        }
    }

    @PostMapping("/send/2")
    public ResponseEntity<String> sendMessage2(@RequestBody User user) {
        try {
            kafkaService.sendMessage2(user);
            return ResponseEntity.ok("Message sent successfully - 2");
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Message error process: " + e.getMessage());
        }
    }
}
