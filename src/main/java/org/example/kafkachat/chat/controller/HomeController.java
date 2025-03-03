package org.example.kafkachat.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController
{
    @GetMapping("/chat/{roomId}")
    public String chatRoom(@PathVariable String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("userId", "testUser"); // 실제 사용자 ID
        model.addAttribute("userName", "Test User"); // 실제 사용자 이름
        return "chat"; // chat.mustache 렌더링
    }

}
