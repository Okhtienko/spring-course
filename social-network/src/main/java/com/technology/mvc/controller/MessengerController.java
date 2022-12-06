package com.technology.mvc.controller;

import com.technology.mvc.dto.MessageDto;
import com.technology.mvc.facade.MessengerFacade;
import com.technology.mvc.model.Message;
import com.technology.mvc.model.User;
import com.technology.mvc.service.UserService;
import com.technology.mvc.session.SignedInUser;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class MessengerController {

  private final MessengerFacade messageFacade;
  private final UserService userService;
  private final SignedInUser signedInUser;

  @GetMapping(path = "/messenger")
  public String showMessages(@RequestParam final Long friendId, final Model model) {
    final List<Message> messages = messageFacade.getMessages(friendId);
    model.addAttribute("messages", messages);
    model.addAttribute("friendId", friendId);

    final User friend = userService.getUserById(friendId);
    model.addAttribute("friendName", friend.getName());
    model.addAttribute("signedUserName", signedInUser.getName());
    return "messenger";
  }

  @PostMapping(path = "/messages",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String sendMessage(final MessageDto messageDto, @RequestParam final Long friendId,
                            @RequestParam final String friendName, final Model model) {
    messageFacade.saveMessage(messageDto, friendId);
    model.addAttribute("friendId", friendId);
    model.addAttribute("friendName", friendName);
    return "redirect:messenger";
  }

  @PostMapping(path = "/clear")
  public String clearHistoryMessages(@RequestParam final Long friendId, final Model model) {
    messageFacade.deleteMessages(friendId);
    model.addAttribute("friendId", friendId);
    return "redirect:messenger";
  }
}
