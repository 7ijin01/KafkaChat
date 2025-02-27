package org.example.kafkachat.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.user.dto.UserRequestDto;
import org.example.kafkachat.user.dto.UserResponseDto;
import org.example.kafkachat.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> userRegister(UserRequestDto.RegisterRequest registerRequest)
    {
        userService.userRegister(registerRequest);
        return ResponseEntity.ok("success");

    }

}
