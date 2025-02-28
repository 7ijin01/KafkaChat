package org.example.kafkachat.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatProducer
{
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(ChatMessage message) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage= mapper.writeValueAsString(message);
        //카프카 브로커로 메세지 보내기
        kafkaTemplate.send("chat-messages", jsonMessage);
    }
}
