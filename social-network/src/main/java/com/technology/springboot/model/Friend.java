package com.technology.springboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_friend_id")
  private Long firstFriendId;

  @Column(name = "second_friend_id")
  private Long secondFriendId;

  @Column(name = "date", insertable = false)
  private Date date;

  public Friend(Long firstFriendId, Long secondFriendId) {
    this.firstFriendId = firstFriendId;
    this.secondFriendId = secondFriendId;
  }
}
