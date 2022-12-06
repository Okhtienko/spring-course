package com.technology.mvc.service;

import com.technology.mvc.hashing.BcryptHashingPassword;
import com.technology.mvc.model.User;
import com.technology.mvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final BcryptHashingPassword hashingPassword;

  public void addUser(String name, String password) {
    userRepository.addUser(name, hashingPassword.generateHash(password));
  }

  public boolean validate(String name, String password) {
    final User user = userRepository.getUser(name).orElse(null);
    return hashingPassword.verifyHash(password, user.getPassword());
  }

  public User getUser(String name) {
    return userRepository.getUser(name).orElse(null);
  }

  public User getUserById(Long signedInUserId) {
    return userRepository.getUserById(signedInUserId).orElse(null);
  }

  public List<User> getSuggestedFriendsList(Long signedInUserId) {
    return userRepository.getSuggestedFriendsList(signedInUserId);
  }

  public List<User> getAllOutgoingRequestList(Long senderId) {
    return userRepository.getAllOutgoingRequestList(senderId);
  }

  public List<User> getAllIncomingRequestList(Long recipientId) {
    return userRepository.getAllIncomingRequestList(recipientId);
  }

  public List<User> getFriendsList(Long signedInUserId) {
    return userRepository.getFriendsList(signedInUserId);
  }

  public boolean isExists(String name) {
    return userRepository.isExists(name);
  }

}
