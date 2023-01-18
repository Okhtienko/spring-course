package com.technology.springboot.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MessageDto {
  @NotNull
  private String text;

  @NotNull
  @NotEmpty
  private Long firstFriendId;

  @NotNull
  @NotEmpty
  private Long secondFriendId;
}
