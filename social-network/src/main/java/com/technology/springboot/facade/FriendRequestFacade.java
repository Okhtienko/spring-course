package com.technology.springboot.facade;

import com.technology.springboot.model.User;
import com.technology.springboot.service.FriendRequestsService;
import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FriendRequestFacade {

  private final FriendRequestsService friendRequestsService;
  private final SignedInUser signedInUser;

  public void addFriendRequest(final Long recipientId) {
    final Long senderId = signedInUser.getId();

    if (!friendRequestsService.isFriendRequestExists(senderId, recipientId)) {
      friendRequestsService.addFriendRequest(senderId, recipientId);
      log.info("Request not exists. Create new request senderId[{}], recipientId[{}]", senderId, recipientId);
    } else {
      log.info("Request is exists. RecipientId[{}]", recipientId);
    }
  }

  public void deleteOutgoingRequest(final Long recipientId) {
    final Long senderId = signedInUser.getId();
    friendRequestsService.deleteFriendRequest(senderId, recipientId);
    log.info("Delete friend request. RecipientId[{}]", recipientId);
  }

  public void deleteIncomingRequest(final Long recipientId) {
    final Long senderId = signedInUser.getId();
    friendRequestsService.deleteFriendRequest(recipientId, senderId);
    log.info("Delete friend request. RecipientId[{}]", recipientId);
  }

  public List<User> viewOutgoingRequests() {
    final Long senderId = signedInUser.getId();
    final List<User> outgoingRequestsList = friendRequestsService.getOutgoingFriendRequests(senderId);
    log.info("Displays a number of requests in friends. Number of requests[{}]", outgoingRequestsList.size());
    return  outgoingRequestsList;
  }

  public List<User> viewIncomingRequests() {
    final Long recipientId = signedInUser.getId();
    final List<User> incomingRequestsList = friendRequestsService.getIncomingFriendRequests(recipientId);
    log.info(
        "Displays a number of incoming requests in friends. Number of incoming requests[{}]",
        incomingRequestsList.size()
    );
    return incomingRequestsList;
  }

}
