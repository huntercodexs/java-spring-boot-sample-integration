package com.huntercodexs.sample.messaging.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class User {
    String name;
    String email;
}
