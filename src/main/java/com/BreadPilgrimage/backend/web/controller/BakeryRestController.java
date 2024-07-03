package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.apiPayload.code.status.SuccessStatus;
import com.BreadPilgrimage.backend.service.BakeryService.BakeryCommandService;
import com.BreadPilgrimage.backend.service.BakeryService.BakeryQueryService;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryTop3DTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bakeries")
public class BakeryRestController {

  private final BakeryCommandService bakeryCommandService;
  private final BakeryQueryService bakeryQueryService;

  @Operation(summary = "빵집 상세페이지 조회 API", description = "빵집 상세페이지 조회 API입니다. 빵집 아이디(bakeryId) PathVariable 입니다! ")
  @GetMapping("/{bakeryId}")
  public ApiResponse<BakeryDetailDTO> getBakeryDetail(@PathVariable("bakeryId") Long bakeryId) {
    BakeryResponseDTO.BakeryDetailDTO result = bakeryQueryService.getBakeryDetail(bakeryId);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵집 지도 정보 API", description = "빵집 지도 정보 API입니다.")
  @GetMapping("/map")
  public ApiResponse<List<BakeryMapDTO>> getBakeryMap() {
    List<BakeryResponseDTO.BakeryMapDTO> result = bakeryQueryService.getBakeryMap();
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵집 지도 검색 API", description = "빵집 지도에서 검색하는 API입니다. 빵집 이름 RequestParam 입니다!")
  @GetMapping("/map/search")
  public ApiResponse<List<BakeryMapDTO>> searchMapBakery(@RequestParam("bakeryName") String bakeryName) {
    List<BakeryResponseDTO.BakeryMapDTO> result = bakeryQueryService.searchMapBakery(bakeryName);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵집 검색 API", description = "빵집 상세 정보 검색 API입니다. 빵집 이름 RequestParam 입니다!")
  @GetMapping("/search")
  public ApiResponse<List<BakeryDetailDTO>> searchBakery(@RequestParam("bakeryName") String bakeryName) {
    List<BakeryDetailDTO> result = bakeryQueryService.searchBakery(bakeryName);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵집 저장하기(찜하기) API", description = "사용자가 빵집을 저장하는 API입니다. 빵집 아이디 PathVariable 입니다!")
  @PostMapping("/{bakeryId}/bookmark")
  public ApiResponse bookmarkBakery(@PathVariable(name = "bakeryId") Long bakeryId, @AuthenticationPrincipal String memberId) {
    bakeryCommandService.bookmarkBakery(Long.parseLong(memberId), bakeryId);
    return ApiResponse.onSuccess(SuccessStatus._OK);
  }

  @Operation(summary = "빵집 저장하기(찜하기) 취소 API", description = "사용자가 저장한 빵집을 취소하는 API입니다. 빵집 아이디 PathVariable 입니다!")
  @DeleteMapping("/{bakeryId}/bookmark")
  public ApiResponse unbookmarkBakery(@PathVariable(name = "bakeryId") Long bakeryId, @AuthenticationPrincipal String memberId) {
    bakeryCommandService.unbookmarkBakery(Long.parseLong(memberId), bakeryId);
    return ApiResponse.onSuccess(SuccessStatus._OK);
  }

  @Operation(summary = "전체 TOP3 빵집 조회 API", description = "전체 TOP3 빵집 조회 API입니다.")
  @GetMapping("/top3")
  public ApiResponse<List<BakeryTop3DTO>> getTotalTop3Bread(){
    List<BakeryTop3DTO> result = bakeryQueryService.getTotalTop3Bakery();
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "빵집 저장 상태 확인 API", description = "사용자가 빵집을 저장했는지 상태를 확인하는 API입니다. 빵집 아이디 PathVariable 입니다!")
  @GetMapping("/{bakeryId}/bookmarkstatus")
  public ApiResponse<Boolean> checkBookmarkStatus(@PathVariable("bakeryId") Long bakeryId, @AuthenticationPrincipal String memberId) {
    return ApiResponse.onSuccess(bakeryQueryService.checkBookmarkStatus(bakeryId, Long.valueOf(memberId)));
  }

}
