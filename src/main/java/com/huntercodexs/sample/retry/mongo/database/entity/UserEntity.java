package com.huntercodexs.sample.retry.mongo.database.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4438788145809229177L;

    @Id
    private String id;

    private String name;
    private String email;

}
