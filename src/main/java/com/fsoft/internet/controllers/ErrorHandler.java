package com.fsoft.internet.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(Throwable.class)
  public String returnError() {
    return "commons/error-page";
  }
}
