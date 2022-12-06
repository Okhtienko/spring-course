package com.technology.mvc.facade;

import com.technology.mvc.dto.UserDto;
import com.technology.mvc.service.UserService;
import com.technology.mvc.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFacade {
  private final UserService userService;
  private final SignedInUser signedInUser;

  public boolean createUser(final UserDto userDto) {
    final String name = userDto.getName();
    final String password = userDto.getPassword();

    userService.addUser(name, password);
    log.info("User does not exist, registering a new user. User[{}]", name);

    return true;
  }

  public boolean checkUserVerification(final UserDto userDto) {
    final String name = userDto.getName();
    final String password = userDto.getPassword();

    if (userService.validate(name, password)) {
      final Long signedInUserId = userService.getUser(name).getId();
      signedInUser.setId(signedInUserId);
      signedInUser.setName(userDto.getName());
      log.info("User logged in successfully. User[{}]", name);
      return true;
    } else {
      log.info("User failed to login. User[{}]", name);
      return false;
    }
  }
}
