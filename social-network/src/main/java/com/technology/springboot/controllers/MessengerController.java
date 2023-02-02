package com.technology.springboot.controllers;

import com.technology.springboot.dto.MessageDto;
import com.technology.springboot.facade.MessengerFacade;
import com.technology.springboot.model.Message;
import com.technology.springboot.model.User;
import com.technology.springboot.service.UserService;
import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class MessengerController {

  private final MessengerFacade messageFacade;
  private final UserService userService;
  private final SignedInUser signedInUser;

  @GetMapping(path = "/messenger")
  public String viewMessages(@RequestParam final Long friendId, final Model model) {
    final List<Message> messages = messageFacade.getMessages(friendId);
    model.addAttribute("messages", messages);
    model.addAttribute("friendId", friendId);

    final User friend = userService.getUserById(friendId);
    model.addAttribute("friend", friend);
    model.addAttribute("signedInUser", signedInUser);

    return "messenger";
  }

  @PostMapping(path = "/messages",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String sendMessage(final MessageDto messageDto, @RequestParam final Long friendId) {
    messageFacade.saveMessage(messageDto, friendId);
    return "redirect:messenger?friendId=" + friendId;
  }

  @PostMapping(path = "/clear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String clearHistoryMessages(@RequestParam final Long friendId) {
    messageFacade.deleteMessages(friendId);
    return "redirect:messenger?friendId=" + friendId;
  }

}
