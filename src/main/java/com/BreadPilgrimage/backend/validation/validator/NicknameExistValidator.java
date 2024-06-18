package com.BreadPilgrimage.backend.validation.validator;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.validation.annotation.ExistEmail;
import com.BreadPilgrimage.backend.validation.annotation.ExistNickname;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NicknameExistValidator implements ConstraintValidator<ExistNickname, String> {

  private final MemberRepository memberRepository;

  @Override
  public void initialize(ExistNickname constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid = memberRepository.existsByNickname(value);

    if (isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ErrorStatus.NICKNAME_ALREADY_EXIST.toString()).addConstraintViolation();
    }

    return !isValid;

  }
}