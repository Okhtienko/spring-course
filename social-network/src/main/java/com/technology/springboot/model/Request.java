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
@Table(name = "requests")
public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "sender_id")
  private Long senderId;

  @Column(name = "recipient_id")
  private Long recipientId;

  @Column(name = "date", insertable = false)
  private Date date;

  public Request(Long senderId, Long recipientId) {
    this.senderId = senderId;
    this.recipientId = recipientId;
  }
}
