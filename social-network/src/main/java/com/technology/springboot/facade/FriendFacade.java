package com.technology.springboot.facade;

import com.technology.springboot.model.User;
import com.technology.springboot.service.FriendRequestsService;
import com.technology.springboot.service.FriendService;
import com.technology.springboot.service.MessengerService;
import com.technology.springboot.service.UserService;
import com.technology.springboot.session.SignedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

  public void addFriend(final Long recipientId) {
    final Long senderId = signedInUser.getId();
    friendService.addFriend(senderId, recipientId);
    log.info("Create new friend. RecipientId[{}]", recipientId);

    friendRequestsService.deleteFriendRequest(recipientId, senderId);
    log.info("Delete friend request. RecipientId[{}]", recipientId);
  }

  public List<User> viewFriends() {
    final Long signedInUserId = signedInUser.getId();
    final List<User> friends = friendService.getFriends(signedInUserId);
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

  public Page<User> viewSuggestedFriends(final int page, final int  size) {
    final Long signedInUserId = signedInUser.getId();
    final Page<User> suggestedFriends = userService.getSuggestedFriendsList(signedInUserId, page, size);
    log.info("Displays a number of suggested friends. Number of suggested friends[{}]",
        suggestedFriends.getTotalElements()
    );
    return suggestedFriends;
  }

}
