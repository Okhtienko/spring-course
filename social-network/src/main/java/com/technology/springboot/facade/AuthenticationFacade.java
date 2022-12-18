package com.technology.springboot.facade;

import com.technology.springboot.dto.UserDto;
import com.technology.springboot.exception.InvalidCredentialException;
import com.technology.springboot.service.UserService;
import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFacade {
  private final UserService userService;
  private final SignedInUser signedInUser;

  public boolean addUser(final UserDto userDto) {
    final String name = userDto.getName();
    final String password = userDto.getPassword();

    userService.addUser(name, password);
    log.info("User does not exist, registering a new user. User[{}]", name);

    return true;
  }

  public boolean checkUserVerification(final UserDto userDto) throws InvalidCredentialException {
    final String name = userDto.getName();
    final String password = userDto.getPassword();

    if (userService.validate(name, password)) {
      final Long signedInUserId = userService.getUserByName(name).getId();
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
