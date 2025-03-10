package org.example.kafkachat.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.chat.dto.ChatRoom;
import org.example.kafkachat.chat.service.ChatRoomService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController
{
    private final ChatRoomService chatRoomService;

    @GetMapping("/rooms")
    public List<ChatRoom> getAllRoomsService()
    {
        return chatRoomService.getRooms();
    }

    @GetMapping("/room/{roomId}")
    public ChatRoom getRoomService(@PathVariable String roomId)
    {
        return chatRoomService.getRoom(roomId);
    }

    @PostMapping("/room")
    public ChatRoom createRoomService(@RequestParam String name)
    {
        ChatRoom room = chatRoomService.createRoom(name);
        System.out.println("Created Room: " + room.getRoomId());  // 서버 로그 확인
        return room;
    }
}
