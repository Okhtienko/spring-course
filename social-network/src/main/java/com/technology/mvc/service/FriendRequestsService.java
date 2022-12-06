package com.technology.mvc.service;

import com.technology.mvc.repository.FriendRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendRequestsService {
  private final FriendRequestsRepository friendRequestsRepository;

  public void createFriendRequest(Long senderId, Long recipientId) {
    friendRequestsRepository.createFriendRequest(senderId, recipientId);
  }

  public void deleteFriendRequest(Long senderId, Long recipientId) {
    friendRequestsRepository.deleteFriendRequest(senderId, recipientId);
  }

  public boolean isRequestExists(Long senderId, Long recipientId) {
    return friendRequestsRepository.isRequestExists(senderId, recipientId);
  }
}
