package com.technology.mvc.service;

import com.technology.mvc.hashing.HashingPasswordRepository;
import com.technology.mvc.model.User;
import com.technology.mvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final HashingPasswordRepository hashingPasswordRepository;

  public void addUser(String name, String password) {
    if (userRepository.getUser(name).isPresent()) {
      throw new RuntimeException("User already exists");
    }
    userRepository.addUser(name, hashingPasswordRepository.generateHash(password));
  }

  public boolean validate(String name, String password) {
    final User user = userRepository.getUser(name).orElse(null);
    if (user != null) {
      return hashingPasswordRepository.verifyHash(password, user.getPassword());
    }
    return false;
  }

  public List<User> filterUsersByName(String parameter) {
    return userRepository.filterUsersByName(parameter);
  }

  public Long getUserId(String name) {
    return userRepository.getUserIdByName(name).orElse(null);
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
