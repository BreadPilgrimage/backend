package com.BreadPilgrimage.backend.web.dto;

import com.BreadPilgrimage.backend.validation.annotation.ExistEmail;
import com.BreadPilgrimage.backend.validation.annotation.ExistNickname;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberRequestDTO {

  @Getter
  public static class JoinDTO{
    @NotBlank(message = "이름이 비어있습니다.")
    String name;

    @NotBlank(message = "닉네임이 비어있습니다.")
    @ExistNickname
    String nickname;

    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @ExistEmail
    @NotBlank
    String email;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    String password;
  }

  @Getter
  public static class LoginDTO{

    @NotBlank(message = "이메일이 비어있습니다.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    String email;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    String password;
  }

}
