package com.technology.mvc.service;

import com.technology.mvc.model.Message;
import com.technology.mvc.repository.MessengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessengerService {
  private final MessengerRepository messageRepository;

  public void saveMessage(String text, Long senderId, Long recipientId) {
    messageRepository.saveMessage(text, senderId, recipientId);
  }

  public List<Message> getMessages(Long senderId, Long recipientId) {
    return messageRepository.getMessages(senderId, recipientId);
  }

  public void deleteMessages(Long senderId, Long recipientId) {
    messageRepository.deleteMessages(senderId, recipientId);
  }
}
