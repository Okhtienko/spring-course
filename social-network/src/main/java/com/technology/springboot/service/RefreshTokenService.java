package com.technology.springboot.service;

import com.technology.springboot.config.jwt.Jwt;
import com.technology.springboot.model.RefreshToken;
import com.technology.springboot.model.User;
import com.technology.springboot.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

  private final Jwt jwt;
  private final RefreshTokenRepository refreshTokenRepository;

  public Optional<RefreshToken> findByRefreshToken(final String refreshToken) {
    return refreshTokenRepository.findByToken(refreshToken);
  }

  public RefreshToken findByUser(final User user) {
    return refreshTokenRepository.findByUser(user);
  }

  public RefreshToken generateRefreshToken(final User user) {

    RefreshToken refreshToken = findByUser(user);

    if (existsByUser(user)) {
      refreshToken.setToken(jwt.generateRefreshToken(user));
      return refreshTokenRepository.save(refreshToken);
    }

    return refreshTokenRepository.save(createRefreshToken(user));
  }

  private RefreshToken createRefreshToken(final User user) {
    return RefreshToken.builder()
        .user(user)
        .token(jwt.generateRefreshToken(user))
        .build();
  }

  public boolean existsByUser(final User user) {
    return refreshTokenRepository.existsByUser(user);
  }

  public RefreshToken validateRefreshToken(final RefreshToken refreshToken) {
    if (!jwt.isValidateRefreshToken(refreshToken.getToken())) {
      refreshTokenRepository.delete(refreshToken);
    }
    return refreshToken;
  }

}
