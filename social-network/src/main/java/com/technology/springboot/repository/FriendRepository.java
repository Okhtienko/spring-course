package com.technology.springboot.repository;

import com.technology.springboot.model.Friend;
import com.technology.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

  @Modifying
  @Query("delete from Friend f where f.firstFriendId = :signedInUserId and f.secondFriendId = :friendId " +
      "or f.secondFriendId = :signedInUserId and f.firstFriendId = :friendId")
  void deleteFriend(@Param("signedInUserId") Long signedInUserId, @Param("friendId") Long friendId);

  @Query(
      "select u from User u where u.id in " +
      "(select u1.id from User u1 join Friend f1 on u1.id = f1.secondFriendId " +
      "where f1.firstFriendId = :signedInUserId) or u.id in " +
      "(select u2.id from User u2 join Friend f2 on u2.id = f2.firstFriendId " +
      "where f2.secondFriendId = :signedInUserId)"
  )
  List<User> getFriends(@Param("signedInUserId") Long signedInUserId);

}
