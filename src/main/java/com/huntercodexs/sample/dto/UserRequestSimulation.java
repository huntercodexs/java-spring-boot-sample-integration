package com.huntercodexs.sample.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestSimulation {

    private String name;
    private String email;

}

