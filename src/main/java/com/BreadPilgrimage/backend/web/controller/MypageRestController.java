package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.service.MyPageService.MypageQueryService;
import com.BreadPilgrimage.backend.web.dto.BreadReviewResponseDTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MyBreadReviewDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageRestController {

  private final MypageQueryService mypageQueryService;

  @Operation(summary = "마이페이지 조회 API", description = "마이페이지 조회 API입니다. 토큰을 입력해주세요.")
  @GetMapping()
  public ApiResponse<MypageResponseDTO.MypageInfoDTO> getMypageInfo(@AuthenticationPrincipal String memberId) {
    MypageResponseDTO.MypageInfoDTO result = mypageQueryService.getMypageInfo(Long.parseLong(memberId));
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "내가 작성한 빵 리뷰 조회 API", description = "내가 작성한 빵 리뷰 조회 API입니다. 토큰을 입력해주세요.")
  @GetMapping("/myreview")
  public ApiResponse<List<MyBreadReviewDTO>> getMyBreadReviews(@AuthenticationPrincipal String memberId) {
    List<MyBreadReviewDTO> result = mypageQueryService.getMyBreadReviews(Long.parseLong(memberId));
    return ApiResponse.onSuccess(result);
  }

}
