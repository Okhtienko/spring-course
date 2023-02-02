package com.technology.springboot.config.jwt;

import com.technology.springboot.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class Jwt {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.refresh}")
  private String jwtRefreshSecret;

  public String generateAccessToken(final User user) {
    Date date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
    return generateToken(user, jwtSecret, date);
  }

  public String generateRefreshToken(final User user) {
    Date date = Date.from(LocalDateTime.now().plusDays(15).atZone(ZoneId.systemDefault()).toInstant());
    return generateToken(user, jwtRefreshSecret, date);
  }

  private String generateToken(final User user, final String secret, final Date date) {
    return Jwts.builder()
        .setSubject(user.getName())
        .setIssuedAt(new Date())
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public boolean isValidateAccessToken(final String accessToken) {
    return isValidateToken(accessToken, jwtSecret);
  }

  public boolean isValidateRefreshToken(final String refreshToken) {
    return isValidateToken(refreshToken, jwtRefreshSecret);
  }

  @SuppressWarnings("PMD")
  private boolean isValidateToken(final String token, final String secret) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      log.info("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      log.info("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      log.info("Malformed jwt");
    } catch (SignatureException sEx) {
      log.info("Invalid signature");
    } catch (Exception e) {
      log.info("invalid token");
    }
    return false;
  }

  public String getNameFromAccessToken(String token) {
    return getNameFromToken(token, jwtSecret);
  }

  private String getNameFromToken(final String token, final String secret) {
    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

}
