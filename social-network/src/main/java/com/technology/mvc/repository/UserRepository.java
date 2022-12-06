package com.technology.mvc.repository;

import com.technology.mvc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  void addUser(String name, String password);

  Optional<User> getUser(String name);
  Optional<User> getUserById(Long signedInUserId);

  List<User> getSuggestedFriendsList(Long signedInUserId);

  List<User> getAllOutgoingRequestList(Long senderId);

  List<User> getAllIncomingRequestList(Long recipientId);

  List<User> getFriendsList(Long signedInUserId);

  boolean isExists(String name);

}
