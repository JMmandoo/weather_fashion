package com.min.fashion.soket;

import com.min.fashion.member.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/public")
  public ChatMessage sendMessage(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    String memberEmail = (String) headerAccessor.getSessionAttributes().get("memberEmail");
    if (memberEmail != null) {
      chatMessage.setSenderEmail(memberEmail); // ChatMessage에 senderEmail 필드가 있어야 함
    }
    return chatMessage;
  }

  @GetMapping("/chat/chat")
  public String chatPage() {
    return "chat/chat";
  }
}



