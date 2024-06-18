package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.service.MemberService.MemberCommandService;
import com.BreadPilgrimage.backend.web.dto.MemberRequestDTO;
import com.BreadPilgrimage.backend.web.dto.MemberResponseDTO;
import com.BreadPilgrimage.backend.web.dto.MemberResponseDTO.JoinResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
  private final MemberCommandService memberCommandService;

  @Operation(summary = "회원 가입 API", description = "회원 가입 API 입니다.")
  @PostMapping("/")
  public ApiResponse<JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request)
  {
    MemberResponseDTO.JoinResultDTO result = memberCommandService.joinMember(request);
    return ApiResponse.onSuccess(result);
  }

}
