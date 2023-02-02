package com.technology.springboot.controllers.rest;

import com.technology.springboot.converter.UserConverter;
import com.technology.springboot.dto.UserDto;
import com.technology.springboot.model.User;
import com.technology.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suggestedfriends")
@RequiredArgsConstructor
public class SuggestedFriendsRestController {

  private final UserConverter userConverter;
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDto>> viewSuggestedFriendsPage(
     @RequestParam(value = "page", defaultValue = "0", required = false) final int page,
     @RequestParam(value = "size", defaultValue = "5", required = false) final int size) {

    final Page<User> pages = userService.getSuggestedFriendsList(page, size);
    final List<User> suggestedFriends = pages.getContent();
    return ResponseEntity.ok(userConverter.toDto(suggestedFriends));
  }

}
