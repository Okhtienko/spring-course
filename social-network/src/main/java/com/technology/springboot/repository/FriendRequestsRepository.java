package com.technology.springboot.repository;

import com.technology.springboot.model.Request;
import com.technology.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FriendRequestsRepository extends JpaRepository<Request, Long> {

  @Modifying
  void deleteFriendRequestBySenderIdAndRecipientId(Long senderId, Long recipientId);

  boolean existsFriendRequestBySenderIdAndRecipientId(Long senderId, Long recipientId);

  @Query("select u from User u inner join Request r on u.id = r.senderId where  r.recipientId = :recipientId")
  List<User> getIncomingRequests(@Param("recipientId") Long recipientId);

  @Query("select u from User u inner join Request r on u.id = r.recipientId where r.senderId = :senderId")
  List<User> getOutgoingRequests(@Param("senderId") Long senderId);

}
