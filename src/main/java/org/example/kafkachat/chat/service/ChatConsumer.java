package org.example.kafkachat.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatConsumer
{
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RedisTemplate<String, String> redisTemplate;
    private static final String CHAT_ROOM_PREFIX = "chat-room:";

    @KafkaListener(topics = "chat-messages",groupId = "chat-group")
    public void listen(String message) throws Exception
    {
        //카프카 브로커에서 메세지 받고 Stomp이용해서 client에게 메세지 뿌리기
        ObjectMapper mapper = new ObjectMapper();
        ChatMessage chatMessage = mapper.readValue(message,ChatMessage.class);
        String chatKey=CHAT_ROOM_PREFIX+chatMessage.getRoomId();
        redisTemplate.opsForList().rightPush(chatKey,message);


        redisTemplate.opsForList().trim(chatKey, -50, -1);
        System.out.println("📥 [KafkaConsumer] 받은 메시지: \n\n\n\n\n" + chatMessage.getContent());
        simpMessagingTemplate.convertAndSend("/topic/chat/"+chatMessage.getRoomId(),chatMessage);
    }

}
