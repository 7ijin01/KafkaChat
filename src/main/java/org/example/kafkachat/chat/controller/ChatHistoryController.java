package org.example.kafkachat.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.example.kafkachat.chat.service.ChatHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/chat")
@RestController
@RequiredArgsConstructor
public class ChatHistoryController
{
    private final ChatHistoryService chatHistoryService;

    @GetMapping("/history/{roomId}")
    public ResponseEntity<List<ChatMessage>> getHistory(@PathVariable String roomId) throws JsonProcessingException {
        List<ChatMessage> chatMessages=chatHistoryService.getRecentMessages(roomId);
        return ResponseEntity.ok(chatMessages);
    }
}
