package com.technology.springboot.service;

import com.technology.springboot.exception.InvalidCredentialException;
import com.technology.springboot.hashing.BcryptHashingPassword;
import com.technology.springboot.model.User;
import com.technology.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final BcryptHashingPassword hashingPassword;

  public void addUser(final String name, final String password) {
    final User user = new User(name, hashingPassword.generateHash(password));
    userRepository.save(user);
  }

  public boolean validate(final String name, final String password) throws InvalidCredentialException {
    final User user = userRepository.findUserByName(name).orElse(null);
    return user != null && hashingPassword.verifyHash(password, user.getPassword());
  }

  public User getUserByName(final String name) {
    return userRepository.findUserByName(name).orElse(null);
  }

  public User getUserById(final Long signedInUserId) {
    return userRepository.findUserById(signedInUserId).orElse(null);
  }

  public List<User> getSuggestedFriendsList(final Long signedInUserId) {
    return userRepository.findSuggestedFriendsByIdNot(signedInUserId);
  }

  public boolean isExists(final String name) {
    return userRepository.existsUserByName(name);
  }
}
