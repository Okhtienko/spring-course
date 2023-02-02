package com.technology.springboot.config.filter;

import com.technology.springboot.config.jwt.Jwt;
import com.technology.springboot.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  private static final String AUTHORIZATION = "Authorization";
  private final Jwt jwtProvider;
  private final AuthenticationService customUserDetailsService;

  @Override
  public void doFilter(
      ServletRequest servletRequest,
      ServletResponse servletResponse,
      FilterChain filterChain

  ) throws IOException, ServletException {

    String token = getTokenFromRequest((HttpServletRequest) servletRequest);
    if (token != null && jwtProvider.isValidateAccessToken(token)) {
      String name = jwtProvider.getNameFromAccessToken(token);
      UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(name);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          customUserDetails, null, customUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION);
    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

}
