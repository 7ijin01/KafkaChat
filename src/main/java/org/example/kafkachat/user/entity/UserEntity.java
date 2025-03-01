package org.example.kafkachat.user.entity;

import lombok.*;
import org.example.kafkachat.user.dto.UserRequestDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@NoArgsConstructor

public class UserEntity {
    @Id
    private String id;
    private String userId;
    private String password;
    private String username;

    @Builder
    private UserEntity(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.username = userName;
    }


}
