package com.fsoft.internet.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = CheckIdExitsValidator.class)
public @interface CheckIdExistsConstraint {
  String message() default "This code is exist!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
