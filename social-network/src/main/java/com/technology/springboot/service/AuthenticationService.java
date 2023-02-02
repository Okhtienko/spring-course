package com.technology.springboot.service;

import com.technology.springboot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    final User user = userService.getUserByName(name);
    return new org.springframework.security.core.userdetails.User(
        user.getName(),
        user.getPassword(),
        new HashSet<>());
  }

}
