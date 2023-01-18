package com.technology.springboot.config;

import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@RequiredArgsConstructor
public class SessionConfig {

  @Bean
  @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = WebApplicationContext.SCOPE_SESSION)
  public SignedInUser signedInUser() {
    return new SignedInUser();
  }

}
