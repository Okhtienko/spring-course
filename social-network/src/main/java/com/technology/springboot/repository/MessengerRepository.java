package com.technology.springboot.repository;

import com.technology.springboot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessengerRepository extends JpaRepository<Message, Long> {

  @Query("select m from Message m where  m.firstFriendId = :senderId and m.secondFriendId = :recipientId " +
      "or m.secondFriendId = :senderId and m.firstFriendId = :recipientId")
  List<Message> getMessages(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

  @Modifying
  @Query("delete from Message m where m.firstFriendId = :senderId and m.secondFriendId = :recipientId " +
      "or m.secondFriendId = :senderId and m.firstFriendId = :recipientId")
  void deleteMessages(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);

}
