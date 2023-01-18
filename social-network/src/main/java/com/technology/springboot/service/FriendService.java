package com.technology.springboot.service;

import com.technology.springboot.model.Friend;
import com.technology.springboot.model.User;
import com.technology.springboot.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

  private final FriendRepository friendRepository;

  public void addFriend(final Long senderId, final Long recipientId) {
    final Friend friend = new Friend(senderId, recipientId);
    friendRepository.save(friend);
  }

  @Transactional
  public void deleteFriend(final Long signedInUserId, final Long friendId) {
    friendRepository.deleteFriend(signedInUserId, friendId);
  }

  public List<User> getFriends(final Long signedInUserId) {
    return friendRepository.getFriends(signedInUserId);
  }

}
