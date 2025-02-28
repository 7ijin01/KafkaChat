package org.example.kafkachat.user.entity;

import lombok.*;
import org.example.kafkachat.user.dto.UserRequestDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    private String id;
    private String userId;
    private String password;
    private String userName;

    @Builder
    private UserEntity(String userId, String password, String userName, String userName1) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }


}
