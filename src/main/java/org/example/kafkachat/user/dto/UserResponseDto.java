package org.example.kafkachat.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class UserResponseDto
{
    @Getter
    @Setter
    @AllArgsConstructor
    public static class RegisterResponse {
        private String username;
        private String userId;
    }
}
