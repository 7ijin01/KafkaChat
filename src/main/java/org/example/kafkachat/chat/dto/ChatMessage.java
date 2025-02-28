package org.example.kafkachat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage
{
    private String roomId;
    private String sender;
    private String content;
    private String timestamp;
}