package com.technology.springboot.config;

import com.technology.springboot.interceptor.AuthorizationInterceptor;
import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
  private final SignedInUser signedInUser;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/signUp").setViewName("signUp");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(
        new MappedInterceptor(
            new String[] {
              "/friends",
              "/outgoingRequests",
              "/incomingRequests",
              "/suggestedFriends",
              "/messenger"
            },
            new AuthorizationInterceptor(signedInUser)
        )
    );
  }
}
