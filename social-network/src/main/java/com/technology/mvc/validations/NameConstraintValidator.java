package com.technology.mvc.validations;

import com.technology.mvc.service.UserService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class NameConstraintValidator implements ConstraintValidator<NameConstraint, String> {
  private final UserService userService;

  @Override
  public boolean isValid(String nameField, ConstraintValidatorContext constraintValidatorContext) {
    return !userService.isExists(nameField);
  }
}
