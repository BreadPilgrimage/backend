package com.BreadPilgrimage.backend.web.dto;

import com.BreadPilgrimage.backend.validation.annotation.ExistEmail;
import com.BreadPilgrimage.backend.validation.annotation.ExistNickname;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberRequestDTO {

  @Getter
  public static class JoinDTO{
    @NotBlank
    String name;

    @NotBlank
    @ExistNickname
    String nickname;

    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @ExistEmail
    @NotBlank
    String email;

    @NotBlank
    String password;
  }

}
