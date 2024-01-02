package com.min.fashion.soket;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
  private String content;
  private String senderEmail; // 메시지를 보낸 사용자의 이메일
}
