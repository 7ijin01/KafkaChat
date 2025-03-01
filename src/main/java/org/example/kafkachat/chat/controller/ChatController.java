package org.example.kafkachat.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.example.kafkachat.chat.service.ChatProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController
{
    private final ChatProducer chatProducer;

    @MessageMapping("/send")
    private ResponseEntity<String> sendMessage(ChatMessage message) throws Exception {
        chatProducer.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}
