package com.BreadPilgrimage.backend.validation.validator;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.validation.annotation.ExistEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailExistValidator implements ConstraintValidator<ExistEmail, String> {

  private final MemberRepository memberRepository;

  @Override
  public void initialize(ExistEmail constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid = memberRepository.existsByEmail(value);

    if (isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.EMAIL_ALREADY_EXIST.toString()).addConstraintViolation();
    }

    return !isValid;

  }
}