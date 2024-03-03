package com.fsoft.internet.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fsoft.internet.services.computer.IComputerService;
import com.fsoft.internet.services.customer.ICustomerService;
import com.fsoft.internet.services.product.IProductService;

public class CheckIdExitsValidator
    implements ConstraintValidator<CheckIdExistsConstraint, String> {

  @Autowired
  private ICustomerService customerService;

  @Autowired
  private IComputerService computerService;

  @Autowired
  private IProductService productService;

  @Override
  public void initialize(CheckIdExistsConstraint constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value.startsWith("CUS")) {
      return customerService.findById(value).isEmpty();
    } else if (value.startsWith("COM")) {
      return computerService.findById(value).isEmpty();
    }
    return productService.findById(value).isEmpty();
  }

}
