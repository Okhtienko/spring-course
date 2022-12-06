package com.technology.mvc.service;

import com.technology.mvc.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {
  private final FriendRepository friendRepository;

  public void addFriend(Long senderId, Long recipientId) {
    friendRepository.addFriend(senderId, recipientId);
  }

  public void deleteFriend(Long signedInUserId, Long friendId) {
    friendRepository.deleteFriend(signedInUserId, friendId);
  }
}
