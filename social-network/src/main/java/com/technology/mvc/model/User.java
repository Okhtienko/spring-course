package com.technology.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

  private Long id;
  private String name;
  private String password;
  private Date date;

}
