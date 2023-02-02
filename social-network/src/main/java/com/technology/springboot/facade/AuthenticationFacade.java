package com.technology.springboot.facade;

import com.technology.springboot.dto.AuthorizationUserDto;
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

  public boolean addUser(final AuthorizationUserDto authorizationUserDto) {
    final String name = authorizationUserDto.getName();
    final String password = authorizationUserDto.getPassword();

    userService.addUser(name, password);
    log.info("User does not exist, registering a new user. User[{}]", name);

    final Long signedInUserId = userService.getUserByName(name).getId();
    signedInUser.setId(signedInUserId);
    signedInUser.setName(authorizationUserDto.getName());

    return true;
  }

  public boolean checkUserVerification(final AuthorizationUserDto authorizationUserDto) {
    final String name = authorizationUserDto.getName();
    final String password = authorizationUserDto.getPassword();

    if (userService.isValidate(name, password)) {
      final Long signedInUserId = userService.getUserByName(name).getId();
      signedInUser.setId(signedInUserId);
      signedInUser.setName(authorizationUserDto.getName());
      log.info("User logged in successfully. User[{}]", name);
      return true;
    } else {
      log.info("User failed to login. User[{}]", name);
      return false;
    }
  }

}
