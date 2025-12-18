package com.huntercodexs.sample.messaging.rabbitmq.dto;

import lombok.Data;

@Data
public class UserEvent {
    private String userId;
    private String name;
    private String email;
}
