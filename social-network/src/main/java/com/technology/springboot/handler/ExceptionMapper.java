package com.technology.springboot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(Exception.class)
  public ModelAndView handleException(final Exception e) {
    log.error("Unexpected exception", e);
    final ModelAndView model = new ModelAndView();
    model.addObject("exceptions", "Interval server error.");
    model.setViewName("errors");
    return model;
  }

  @ExceptionHandler(BindException.class)
  public ModelAndView handleException(final BindException e) {
    log.error("Unexpected exception", e);
    final ModelAndView model = new ModelAndView();
    final List<String> exceptions = e.getAllErrors()
        .stream()
        .map(value -> Optional.ofNullable(value.getDefaultMessage()).orElse("Unknown error"))
        .toList();
    model.addObject("exceptions", exceptions);
    model.setViewName("errors");
    return model;
  }

}
