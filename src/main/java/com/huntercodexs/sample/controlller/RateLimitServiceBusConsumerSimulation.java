package com.huntercodexs.sample.controlller;

import com.huntercodexs.integration.ratelimit.annotation.RateLimitServiceBus;
import com.huntercodexs.sample.dto.ProcessMessageSimulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RateLimitServiceBusConsumerSimulation {

    private static final Logger log = LoggerFactory.getLogger(RateLimitServiceBusConsumerSimulation.class);

    @PostMapping("/simulate-queue-process")
    @RateLimitServiceBus(limit = 3, duration = 10, unit = TimeUnit.SECONDS, keyParameterName = "__MESSAGE__")
    public ResponseEntity<String> processMessage(@RequestBody ProcessMessageSimulation __MESSAGE__) {
        log.info("Processing message for UserID: {}", __MESSAGE__.getUserId());
        return ResponseEntity.ok("Message processed successfully for userId: " + __MESSAGE__.getUserId());
    }

    @PostMapping("/simulate-queue-process2")
    @RateLimitServiceBus(limit = 5, duration = 10, unit = TimeUnit.SECONDS, keyParameterName = "user")
    public ResponseEntity<String> processMessage2(@RequestBody ProcessMessageSimulation user) {
        log.info("Processing message for User: {}", user.getUserId());
        return ResponseEntity.ok("Message processed successfully for user: " + user.getUserId());
    }
}
