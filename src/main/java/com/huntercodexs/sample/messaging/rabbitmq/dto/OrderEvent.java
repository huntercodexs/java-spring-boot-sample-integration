package com.huntercodexs.sample.messaging.rabbitmq.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private String orderId;
    private String product;
    private int quantity;
}
