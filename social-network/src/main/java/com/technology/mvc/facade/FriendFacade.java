package com.technology.mvc.facade;

import com.technology.mvc.model.User;
import com.technology.mvc.service.FriendRequestsService;
import com.technology.mvc.service.FriendService;
import com.technology.mvc.service.MessengerService;
import com.technology.mvc.service.UserService;
import com.technology.mvc.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FriendFacade {
  private final FriendService friendService;
  private final FriendRequestsService friendRequestsService;
  private final UserService userService;
  private final SignedInUser signedInUser;

  private final MessengerService messengerService;

  public void createFriend(final Long recipientId) {
    final Long senderId = signedInUser.getId();
    friendService.addFriend(senderId, recipientId);
    log.info("Create new friend. RecipientId[{}]", recipientId);

    friendRequestsService.deleteFriendRequest(recipientId, senderId);
    log.info("Delete friend request. RecipientId[{}]", recipientId);
  }

  public List<User> showFriends() {
    final Long signedInUserId = signedInUser.getId();
    final List<User> friends = userService.getFriendsList(signedInUserId);
    log.info("Displays a number of friends. Number of friends[{}]", friends.size());
    return friends;
  }

  public void deleteFriend(final Long recipientId) {
    final Long signedInUserId = signedInUser.getId();
    friendService.deleteFriend(signedInUserId, recipientId);
    log.info("Delete friend. FriendId[{}]", recipientId);

    messengerService.deleteMessages(signedInUserId, recipientId);
    log.info("Delete messages. FriendId[{}]", recipientId);
  }

  public List<User> showSuggestedFriends() {
    final Long signedInUserId = signedInUser.getId();
    final List<User> suggestedFriends = userService.getSuggestedFriendsList(signedInUserId);
    log.info("Displays a number of suggested friends. Number of suggested friends[{}]",
        suggestedFriends.size()
    );
    return suggestedFriends;
  }
}
