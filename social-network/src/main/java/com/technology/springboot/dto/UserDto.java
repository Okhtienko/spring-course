package com.technology.springboot.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

  @NotNull
  @NotEmpty
  private Long id;

  @NotNull
  @NotEmpty
  private String name;

}
