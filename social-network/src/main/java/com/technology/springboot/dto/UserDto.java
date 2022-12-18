package com.technology.springboot.dto;

import com.technology.springboot.validations.Credentials;
import com.technology.springboot.validations.NameUnique;
import com.technology.springboot.validations.Unique;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
  @NotNull(groups = {Unique.class, Credentials.class})
  @NotEmpty(groups = {Unique.class, Credentials.class}, message = "Name cannot be empty.")
  @Size(
      groups = {Unique.class, Credentials.class},
      min = 2,
      max = 30,
      message = "Name must be between 2 and 30 characters."
  )
  @NameUnique(groups = Unique.class)
  private String name;

  @NotNull(groups = {Unique.class, Credentials.class})
  @NotEmpty(groups = {Unique.class, Credentials.class}, message = "Password cannot be empty.")
  @Size(
      groups = {Unique.class, Credentials.class},
      min = 6,
      max = 15,
      message = "Password must be between 6 and 15 characters."
  )
  private String password;
}
