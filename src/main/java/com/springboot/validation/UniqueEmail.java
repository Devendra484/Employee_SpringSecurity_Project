package com.springboot.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EmailExistsValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueEmail {
  String message() default "Email already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
