package com.technology.springboot.interceptor;

import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
  private final SignedInUser signedInUser;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (signedInUser.getId() != null && signedInUser.getName() != null) {
      return true;
    }
    response.sendRedirect("signUp");
    return false;
  }
}
