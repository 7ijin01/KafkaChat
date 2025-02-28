package org.example.kafkachat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDto
{
    @Getter
    @Setter
    @AllArgsConstructor
    public static class RegisterRequest {
        private String userId;
        private String password;
        private String userName;
    }
}
