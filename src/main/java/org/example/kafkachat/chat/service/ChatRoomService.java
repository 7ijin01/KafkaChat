package org.example.kafkachat.chat.service;


import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatRoom;
import org.example.kafkachat.chat.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService
{
    private final ChatRoomRepository chatRoomRepository;

    
    public List<ChatRoom> getRooms()
    {
        return chatRoomRepository.findAllRooms();
    }
    public ChatRoom getRoom(String roomId)
    {
        return chatRoomRepository.findRoomById(roomId);
    }

    public ChatRoom createRoom (String roomName)
    {
        return  chatRoomRepository.createChatRoom(roomName);
    }
}
