package com.technology.mvc.repository;

public interface FriendRepository {
  void addFriend(Long senderId, Long recipientId);

  void deleteFriend(Long signedInUserId, Long friendId);
}
