package com.technology.springboot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InvalidCredentialException extends Exception {
  private static final String MESSAGE = "User does not exist";
}
