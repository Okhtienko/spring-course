package com.technology.mvc.controller;

import com.technology.mvc.facade.FriendFacade;
import com.technology.mvc.model.User;
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
  public String renderSuggestedFriendsPage(final Model model) {
    final List<User> suggestedFriends = friendFacade.showSuggestedFriends();
    model.addAttribute("suggestedFriends", suggestedFriends);
    return "suggestedFriends";
  }
}
