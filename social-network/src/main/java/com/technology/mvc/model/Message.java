package com.technology.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Message {

  private Long id;
  private Long firstFriendId;
  private Long secondFriendId;
  private String text;
  private Date date;

}
