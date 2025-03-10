package org.example.kafkachat.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryService
{
    private final RedisTemplate<String, String> redisTemplate;
    private static final String CHAT_HISTORY_TOPIC = "chat-room";

    public List<ChatMessage> getRecentMessages(String roomId) throws JsonProcessingException
    {
        String key = CHAT_HISTORY_TOPIC + roomId;
        ObjectMapper mapper = new ObjectMapper();
        List<String> messagesJson = redisTemplate.opsForList().range(key, 0, -1);
        List<ChatMessage> messages = new ArrayList<>();

        if (messagesJson != null) {
            for (String msgJson : messagesJson) {
                messages.add(mapper.readValue(msgJson, ChatMessage.class));
            }
        }
        return messages;
    }


}
