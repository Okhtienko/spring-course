package com.technology.springboot.controller;

import com.technology.springboot.facade.FriendFacade;
import com.technology.springboot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SuggestedFriendsController {

  private final FriendFacade friendFacade;

  @GetMapping(path = "/suggestedFriends")
  public String viewSuggestedFriendsPage(
      final Model model,
      @RequestParam(value = "page", defaultValue = "0", required = false) final int page,
      @RequestParam(value = "size", defaultValue = "5", required = false) final int size) {

    final int[] sizes = new int[]{5, 10, 15};
    final Page<User> pages = friendFacade.viewSuggestedFriends(page, size);
    final List<User> suggestedFriends = pages.getContent();

    model.addAttribute("size", size);
    model.addAttribute("sizes", sizes);
    model.addAttribute("currentPage", page);
    model.addAttribute("pages", pages.getTotalPages());
    model.addAttribute("items", pages.getTotalElements());
    model.addAttribute("suggestedFriends", suggestedFriends);

    return "suggestedFriends";
  }

  @PostMapping(path = "/suggestedFriends", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String getSize(@RequestParam final int size) {
    return "redirect:suggestedFriends?size=" + size;
  }

}
