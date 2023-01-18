package com.technology.springboot.facade;

import com.technology.springboot.dto.MessageDto;
import com.technology.springboot.model.Message;
import com.technology.springboot.service.MessengerService;
import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessengerFacade {

  private final SignedInUser signedInUser;
  private final MessengerService messageService;

  public void saveMessage(final MessageDto messageDto, final Long recipientId) {
    final Long senderId = signedInUser.getId();
    final String text = messageDto.getText();
    messageService.saveMessage(text, senderId, recipientId);
    log.info("Save message to bd. Message text[{}]. SenderId[{}]. RecipientId[{}]", text, senderId, recipientId);
  }

  public List<Message> getMessages(final Long recipientId) {
    final Long senderId = signedInUser.getId();
    final List<Message> messages = messageService.getMessages(senderId, recipientId);
    log.info("Displays a number of messages. Number of Messages[{}]", messages.size());
    return messages;
  }

  public void deleteMessages(final Long recipientId) {
    final Long senderId = signedInUser.getId();
    messageService.deleteMessages(senderId, recipientId);
    log.info("Delete messages from db. SenderId[{}]. RecipientId[{}]", senderId, recipientId);
  }

}
