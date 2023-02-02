package com.technology.springboot.service;

import com.technology.springboot.config.security.PasswordConfig;
import com.technology.springboot.model.User;
import com.technology.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordConfig passwordConfig;

  public User addUser(final String name, final String password) {
    final User user = User
        .builder()
        .name(name)
        .password(passwordConfig.passwordEncoder().encode(password))
        .build();
    return userRepository.save(user);
  }

  public boolean isValidate(final String name, final String password) {
    final User user = userRepository.findUserByName(name).orElse(null);
    return user != null && passwordConfig.passwordEncoder().matches(password, user.getPassword());
  }

  public User getUserByName(final String name) {
    return userRepository.findUserByName(name).orElse(null);
  }

  public User getUserById(final Long signedInUserId) {
    return userRepository.findUserById(signedInUserId).orElse(null);
  }

  public Page<User> getSuggestedFriendsList(final Long signedInUserId, final int page, final int  size) {
    return userRepository.findSuggestedFriendsByIdNot(signedInUserId, PageRequest.of(page, size));
  }

  public Page<User> getSuggestedFriendsList(final int page, final int  size) {
    final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final String name = userDetails.getUsername();
    return userRepository.findSuggestedFriendsByNameNot(name, PageRequest.of(page, size));
  }

  public boolean isExists(final String name) {
    return userRepository.existsUserByName(name);
  }

}
