package com.technology.mvc.controller;

import com.technology.mvc.facade.FriendFacade;
import com.technology.mvc.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FriendController {
  private final FriendFacade friendFacade;

  @GetMapping(path = "/friends")
  public String showFriends(final Model model) {
    final List<User> friends = friendFacade.showFriends();
    model.addAttribute("friends", friends);
    return "friends";
  }

  @PostMapping(path = "/acceptingRequests",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String createFriend(@RequestParam final Long requestFriendId) {
    friendFacade.createFriend(requestFriendId);
    return "redirect:incomingRequests";
  }

  @PostMapping(path = "/friends",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String deleteFriend(@RequestParam final Long requestFriendId) {
    friendFacade.deleteFriend(requestFriendId);
    return "friends";
  }
}
