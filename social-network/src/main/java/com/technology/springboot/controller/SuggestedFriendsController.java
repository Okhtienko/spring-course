package com.technology.springboot.controller;

import com.technology.springboot.facade.FriendFacade;
import com.technology.springboot.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/suggestedFriends")
public class SuggestedFriendsController {
  private final FriendFacade friendFacade;

  @GetMapping()
  public String viewSuggestedFriendsPage(final Model model) {
    final List<User> suggestedFriends = friendFacade.viewSuggestedFriends();
    model.addAttribute("suggestedFriends", suggestedFriends);
    return "suggestedFriends";
  }
}
