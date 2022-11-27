package com.technology.mvc.dto;

import com.technology.mvc.validations.NameConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

  @NotNull
  @NotEmpty(message = "Name cannot be empty.")
  @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters.")
  @NameConstraint
  private String name;

  @NotNull
  @NotEmpty(message = "Password cannot be empty.")
  @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters.")
  private String password;
}
