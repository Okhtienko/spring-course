package com.technology.mvc.controller;

import com.technology.mvc.dto.UserDto;
import com.technology.mvc.facade.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationFacade authorizationFacade;

  @GetMapping(path = "/signUp")
  public String renderSignUpPage(final Model model) {
    model.addAttribute("userDto", new UserDto());
    return "signUp";
  }

  @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String signUp(@Valid  @ModelAttribute("userDto") final UserDto userDto) {
    return authorizationFacade.createUser(userDto)
        ? "welcome"
        : "redirect:signUp";
  }

  @PostMapping(path = "/signIn", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String signIn(@Valid final UserDto userDto) {
    return authorizationFacade.checkUserVerification(userDto)
        ? "redirect:suggestedFriends"
        : "redirect:signUp";
  }
}
