package com.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.Repository.UsersRepository;

public class EmailExistsValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    return !usersRepository.existsByEmail(email);
  }
}
