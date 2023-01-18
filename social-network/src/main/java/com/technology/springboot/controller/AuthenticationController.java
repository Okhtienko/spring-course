package com.technology.springboot.controller;

import com.technology.springboot.dto.UserDto;
import com.technology.springboot.exception.InvalidCredentialException;
import com.technology.springboot.facade.AuthenticationFacade;
import com.technology.springboot.validations.Credentials;
import com.technology.springboot.validations.Unique;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationFacade authorizationFacade;

  @GetMapping(path = "/signUp")
  public String viewSignUpPage(final Model model) {
    model.addAttribute("userDto", new UserDto());
    return "signUp";
  }

  @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String signUp(@Validated(Unique.class)  @ModelAttribute("userDto") final UserDto userDto) {
    return authorizationFacade.addUser(userDto)
        ? "redirect:suggestedFriends"
        : "redirect:signUp";
  }

  @PostMapping(path = "/signIn", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String signIn(@Validated(Credentials.class) final UserDto userDto) throws InvalidCredentialException {
    return authorizationFacade.checkUserVerification(userDto)
        ? "redirect:suggestedFriends"
        : "redirect:signUp";
  }

}
