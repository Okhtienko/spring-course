package com.technology.mvc.repository;

import com.technology.mvc.model.Message;

import java.util.List;

public interface MessengerRepository {
  void saveMessage(String text, Long senderId, Long recipientId);
  List<Message> getMessages(Long senderId, Long recipientId);

  void deleteMessages(Long senderId, Long recipientId);
}
