package com.technology.springboot.service;

import com.technology.springboot.model.Request;
import com.technology.springboot.model.User;
import com.technology.springboot.repository.FriendRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendRequestsService {

  private final FriendRequestsRepository friendRequestsRepository;

  public void addFriendRequest(final Long senderId, final Long recipientId) {
    final Request friendRequest = new Request(senderId, recipientId);
    friendRequestsRepository.save(friendRequest);
  }

  @Transactional
  public void deleteFriendRequest(final Long senderId, final Long recipientId) {
    friendRequestsRepository.deleteFriendRequestBySenderIdAndRecipientId(senderId, recipientId);
  }

  public boolean isFriendRequestExists(final Long senderId, final Long recipientId) {
    return friendRequestsRepository.existsFriendRequestBySenderIdAndRecipientId(senderId, recipientId);
  }

  public List<User> getIncomingFriendRequests(final Long recipientId) {
    return friendRequestsRepository.getIncomingRequests(recipientId);
  }

  public List<User> getOutgoingFriendRequests(final Long senderId) {
    return friendRequestsRepository.getOutgoingRequests(senderId);
  }

}
