package com.technology.springboot.validations;

import com.technology.springboot.service.UserService;
import lombok.AllArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class NameUniqueValidator implements ConstraintValidator<NameUnique, String> {
  private final UserService userService;

  @Override
  public boolean isValid(String nameField, ConstraintValidatorContext constraintValidatorContext) {
    return !userService.isExists(nameField);
  }
}
