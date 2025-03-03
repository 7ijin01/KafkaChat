package org.example.kafkachat.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatProducer
{
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Transactional
    public void sendMessage(ChatMessage message, String roomId,
                            SimpMessageHeaderAccessor simpMessageHeaderAccessor) throws Exception
    {
        if(roomId==null || message==null)
        {
            throw new  Exception("roomId and message");
        }

        message.setSender(simpMessageHeaderAccessor.getSessionAttributes().get("username").toString());

        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage= mapper.writeValueAsString(message);
        //카프카 브로커로 메세지 보내기
        System.out.println("📥 [Kafka프로듀서] 보낸 메시지: \n\n\n\n\n" + message.getContent());
        kafkaTemplate.send("chat-messages", jsonMessage);
    }
}
