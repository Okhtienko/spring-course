package com.technology.springboot.controllers;

import com.technology.springboot.dto.AuthorizationUserDto;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationFacade authorizationFacade;

  @GetMapping(path = "/signUp")
  public String viewSignUpPage(final Model model) {
    model.addAttribute("authorizationUserDto", new AuthorizationUserDto());
    return "signUp";
  }

  @PostMapping(path = "/signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String signUp(
      @Validated(Unique.class) @ModelAttribute("authorizationUserDto") final AuthorizationUserDto authorizationUserDto
  ) {

    return authorizationFacade.addUser(authorizationUserDto)
        ? "redirect:suggestedFriends"
        : "redirect:signUp";
  }

  @PostMapping(path = "/signIn", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String signIn(@Validated(Credentials.class) final AuthorizationUserDto authorizationUserDto) {
    return authorizationFacade.checkUserVerification(authorizationUserDto)
        ? "redirect:suggestedFriends"
        : "redirect:signUp";
  }

}
