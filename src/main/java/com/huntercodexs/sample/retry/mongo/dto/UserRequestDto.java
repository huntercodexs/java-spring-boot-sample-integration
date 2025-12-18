package com.huntercodexs.sample.retry.mongo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRequestDto {
    private String name;
    private String email;
}
