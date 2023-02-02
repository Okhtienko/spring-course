package com.technology.springboot.service;

import com.technology.springboot.model.Message;
import com.technology.springboot.repository.MessengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessengerService {

  private final MessengerRepository messageRepository;

  public void saveMessage(final String text, final Long senderId, final Long recipientId) {
    final Message message = Message
        .builder()
        .firstFriendId(senderId)
        .secondFriendId(recipientId)
        .text(text)
        .build();
    messageRepository.save(message);
  }

  public List<Message> getMessages(final Long senderId, final Long recipientId) {
    return messageRepository.getMessages(senderId, recipientId);
  }

  @Transactional
  public void deleteMessages(final Long senderId, final Long recipientId) {
    messageRepository.deleteMessages(senderId, recipientId);
  }

}
