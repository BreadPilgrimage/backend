package com.BreadPilgrimage.backend.validation.annotation;

import com.BreadPilgrimage.backend.validation.validator.EmailExistValidator;
import com.BreadPilgrimage.backend.validation.validator.NicknameExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NicknameExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistNickname {

  String message() default "이미 존재하는 닉네임입니다.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}