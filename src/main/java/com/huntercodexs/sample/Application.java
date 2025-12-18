package com.huntercodexs.sample;

import com.huntercodexs.integration.core.annotation.EnableIntegration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableIntegration("com.huntercodexs.sample")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
