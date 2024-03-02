package com.fsoft.internet.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fsoft.internet.services.customer.ICustomerService;

public class CheckEmailExitsValidator
    implements ConstraintValidator<CheckEmailExitsConstraint, String> {
  @Autowired
  private ICustomerService customerService;

  @Override
  public void initialize(CheckEmailExitsConstraint constraintAnnotation) {
  }

  /**
   * @author DuyNT58
   * @Author_birth_date: 1995-01-01
   * @param email
   * @param context
   * @return
   * @TODO
   */
  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return customerService.findByEmail(email).isEmpty();
  }
}
