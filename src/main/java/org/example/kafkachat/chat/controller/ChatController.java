package org.example.kafkachat.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.example.kafkachat.chat.service.ChatProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class ChatController
{
    private final ChatProducer chatProducer;

    @MessageMapping("/send/{chatRoomId}")
    private ResponseEntity<String> sendMessage(@Payload ChatMessage message,
                                               @DestinationVariable String chatRoomId,
                                               SimpMessageHeaderAccessor headerAccessor) throws Exception
    {
        chatProducer.sendMessage(message,chatRoomId,headerAccessor);
        return ResponseEntity.ok().build();
    }
}
