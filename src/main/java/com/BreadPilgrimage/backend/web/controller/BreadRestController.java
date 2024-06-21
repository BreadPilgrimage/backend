package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.apiPayload.code.status.SuccessStatus;
import com.BreadPilgrimage.backend.service.BreadService.BreadCommandService;
import com.BreadPilgrimage.backend.service.BreadService.BreadQueryService;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadPreViewDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/breads")
public class BreadRestController {

  private final BreadQueryService breadQueryService;
  private final BreadCommandService breadCommandService;

  @Operation(summary = "빵 전체 리스트 조회 API", description = "빵 전체 리스트 조회 API입니다. 빵집 아이디(bakeryId) PathVariable 입니다! ")
  @GetMapping("/{bakeryId}")
  public ApiResponse<List<BreadPreViewDTO>> getAllBreadList(@PathVariable("bakeryId") Long bakeryId){
    List<BreadPreViewDTO> result = breadQueryService.getAllBreadList(bakeryId);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵집 내 TOP3 빵 조회 API", description = "빵집 내 TOP3 빵 조회 API입니다. 빵집 아이디(bakeryId) PathVariable 입니다! ")
  @GetMapping("/{bakeryId}/top3")
  public ApiResponse<List<BreadPreViewDTO>> getTop3Bread(@PathVariable("bakeryId") Long bakeryId){
    List<BreadPreViewDTO> result = breadQueryService.getTop3Bread(bakeryId);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵 상세페이지 조회 API", description = "빵 상세페이지 조회 API입니다. 빵 아이디(breadId) PathVariable 입니다! ")
  @GetMapping("/{breadId}/detail")
  public ApiResponse<BreadDetailDTO> getBreadDetail(@PathVariable("breadId") Long breadId) {
    BreadResponseDTO.BreadDetailDTO result = breadQueryService.getBreadDetail(breadId);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵 추천(좋아요)하기 API", description = "빵 추천(좋아요) API입니다. 빵 아이디(breadId) PathVariable 입니다!")
  @PostMapping("/{breadId}/like")
  public ApiResponse addBreadLike(@PathVariable(name = "breadId") Long breadId, @AuthenticationPrincipal String memberId) {
    breadCommandService.addBreadLike(Long.parseLong(memberId), breadId);
    return ApiResponse.onSuccess(SuccessStatus._OK);
  }

  @Operation(summary = "빵 추천(좋아요) 취소하기 API", description = "빵 추천(좋아요)을 취소하는 API입니다. 빵 아이디(breadId) PathVariable 입니다!")
  @DeleteMapping("/{breadId}/deletelike")
  public ApiResponse deleteBreadLike(@PathVariable(name = "breadId") Long breadId, @AuthenticationPrincipal String memberId) {
    breadCommandService.deleteBreadLike(Long.parseLong(memberId), breadId);
    return ApiResponse.onSuccess(SuccessStatus._OK);
  }

}
