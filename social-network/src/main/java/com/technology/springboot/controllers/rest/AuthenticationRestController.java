package com.technology.springboot.controllers.rest;

import com.technology.springboot.config.jwt.Jwt;
import com.technology.springboot.converter.UserConverter;
import com.technology.springboot.dto.AuthorizationUserDto;
import com.technology.springboot.dto.JwtResponseDto;
import com.technology.springboot.dto.TokenRefreshRequestDto;
import com.technology.springboot.dto.UserDto;
import com.technology.springboot.model.RefreshToken;
import com.technology.springboot.model.User;
import com.technology.springboot.service.RefreshTokenService;
import com.technology.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationRestController {

  private final Jwt jwt;
  private final UserService userService;
  private final UserConverter userConverter;
  private final RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponseDto> signIn(@Valid @RequestBody AuthorizationUserDto authorizationUserDto) {
    final String name = authorizationUserDto.getName();
    final String password = authorizationUserDto.getPassword();

    if (userService.isValidate(name, password)) {
      final User user = userService.getUserByName(name);
      String accessToken = jwt.generateAccessToken(user);
      RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
      return ResponseEntity.ok(new JwtResponseDto(user.getId(), user.getName(), accessToken, refreshToken.getToken()));
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<UserDto> signUp(@Valid @RequestBody AuthorizationUserDto authorizationUserDto) {
    final String name = authorizationUserDto.getName();
    final String password = authorizationUserDto.getPassword();

    if (userService.isExists(name)) {
      return ResponseEntity.noContent().build();
    }
    final User user = userService.addUser(name, password);

    return ResponseEntity.ok(userConverter.toDto(user));

  }

  @PostMapping("/refresh")
  public ResponseEntity<TokenRefreshRequestDto> refreshToken(
      @Valid @RequestBody final TokenRefreshRequestDto tokenRefreshRequestDto) {

    String requestRefreshToken  = tokenRefreshRequestDto.getRefreshToken();
    return refreshTokenService.findByRefreshToken(requestRefreshToken)
        .map(refreshTokenService::validateRefreshToken)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwt.generateAccessToken(user);
          return ResponseEntity.ok(new TokenRefreshRequestDto(token, requestRefreshToken));
        }).orElse(ResponseEntity.noContent().build());
  }

}
