package com.BreadPilgrimage.backend.apiPayload.code.status;

import com.BreadPilgrimage.backend.apiPayload.code.BaseErrorCode;
import com.BreadPilgrimage.backend.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

  // 가장 일반적인 응답
  _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
  _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
  _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
  _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


  // 멤버 관려 에러
  MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
  NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임이 이미 존재합니다."),
  EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4003", "이메일이 이미 존재합니다."),
  MEMBER_LOGIN_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4004", "로그인 실패 : 이메일이나 비밀번호가 일치하지 않음");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  @Override
  public ErrorReasonDTO getReason() {
    return ErrorReasonDTO.builder()
        .message(message)
        .code(code)
        .isSuccess(false)
        .build();
  }

  @Override
  public ErrorReasonDTO getReasonHttpStatus() {
    return ErrorReasonDTO.builder()
        .message(message)
        .code(code)
        .isSuccess(false)
        .httpStatus(httpStatus)
        .build()
        ;
  }
}