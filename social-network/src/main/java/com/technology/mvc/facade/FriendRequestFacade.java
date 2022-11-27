package com.technology.mvc.facade;

import com.technology.mvc.model.User;
import com.technology.mvc.service.FriendRequestsService;
import com.technology.mvc.service.UserService;
import com.technology.mvc.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FriendRequestFacade {
  private final FriendRequestsService friendRequestsService;
  private final UserService userService;
  private final SignedInUser signedInUser;

  public void createFriendRequest(final Long recipientId) {
    final Long senderId = signedInUser.getId();

    if (!friendRequestsService.isRequestExists(senderId, recipientId)) {
      friendRequestsService.createFriendRequest(senderId, recipientId);
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

  public List<User> renderOutgoingRequestsPage() {
    final Long senderId = signedInUser.getId();
    final List<User> outgoingRequestsList = userService.getAllOutgoingRequestList(senderId);
    log.info("Displays a number of requests in friends. Number of requests[{}]", outgoingRequestsList.size());
    return  outgoingRequestsList;
  }

  public List<User> renderIncomingRequestsPage() {
    final Long recipientId = signedInUser.getId();
    final List<User> incomingRequestsList = userService.getAllIncomingRequestList(recipientId);
    log.info(
        "Displays a number of incoming requests in friends. Number of incoming requests[{}]",
        incomingRequestsList.size()
    );
    return incomingRequestsList;
  }
}
