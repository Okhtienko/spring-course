package com.technology.mvc.handler;

import com.technology.mvc.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();
    model.addObject("exceptions", exceptions);
    model.setViewName("errors");
    return model;
  }
}
