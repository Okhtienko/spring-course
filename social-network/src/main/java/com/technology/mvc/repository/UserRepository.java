package com.technology.mvc.repository;

import com.technology.mvc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  void addUser(String name, String password);

  boolean validate(String name, String password);

  List<User> findUsers();

  Optional<User> getUser(String name);

  List<User> filterUsersByName(String parameter);

  Optional<Long> getUserIdByName(String name);

  List<User> getSuggestedFriendsList(Long signedInUserId);

  List<User> getAllOutgoingRequestList(Long senderId);

  List<User> getAllIncomingRequestList(Long recipientId);

  List<User> getFriendsList(Long signedInUserId);

  boolean isExists(String name);

}
