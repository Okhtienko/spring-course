package com.technology.mvc.controller;

import com.technology.mvc.facade.FriendRequestFacade;
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
public class FriendRequestController {

  private final FriendRequestFacade friendRequestFacade;

  @PostMapping(path = "/creatingFriendRequests", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String sendFriendRequest(@RequestParam final Long requestFriendId) {
    friendRequestFacade.createFriendRequest(requestFriendId);
    return "redirect:suggestedFriends";
  }

  @PostMapping(path = "/outgoingRequests",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String deleteOutgoingRequest(@RequestParam final Long requestFriendId) {
    friendRequestFacade.deleteOutgoingRequest(requestFriendId);
    return "redirect:outgoingRequests";
  }

  @GetMapping(path = "/outgoingRequests")
  public String showOutgoingRequests(final Model model) {
    final List<User> outgoingRequestsList = friendRequestFacade.showOutgoingRequests();
    model.addAttribute("outgoingRequestsList", outgoingRequestsList);
    return "outgoingRequests";
  }

  @GetMapping(path = "/incomingRequests")
  public String showIncomingRequests(final Model model) {
    final List<User> incomingRequestsList = friendRequestFacade.showIncomingRequests();
    model.addAttribute("incomingRequestsList", incomingRequestsList);
    return "incomingRequests";
  }

  @PostMapping(path = "/incomingRequests",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String deleteIncomingRequest(@RequestParam final Long requestFriendId) {
    friendRequestFacade.deleteIncomingRequest(requestFriendId);
    return "redirect:incomingRequests";
  }
}
