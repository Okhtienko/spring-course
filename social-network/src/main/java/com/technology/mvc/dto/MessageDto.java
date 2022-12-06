package com.technology.mvc.dto;

import lombok.Data;

@Data
public class MessageDto {

  private String text;
  private Long firstFriendId;
  private Long secondFriendId;

}
