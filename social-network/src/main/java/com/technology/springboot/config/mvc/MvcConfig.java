package com.technology.springboot.config.mvc;

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
    registry.addViewController("/view/signUp").setViewName("signUp");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(
        new MappedInterceptor(
            new String[]{
              "/view/friends",
              "/view/outgoingRequests",
              "/view/incomingRequests",
              "/view/suggestedFriends",
              "/view/messenger"
            },
            new AuthorizationInterceptor(signedInUser)
        )
    );
  }

}
