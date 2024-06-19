package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.service.BakeryService.BakeryCommandService;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bakeries")
public class BakeryRestController {

  private final BakeryCommandService bakeryCommandService;

  @Operation(summary = "빵집 상세페이지 조회 API", description = "빵집 상세페이지 조회 API입니다. 빵집 아이디(bakeryId) PathVariable 입니다! ")
  @GetMapping("/{bakeryId}")
  public ApiResponse<BakeryDetailDTO> getBakeryDetail(@PathVariable("bakeryId") Long bakeryId) {
    BakeryResponseDTO.BakeryDetailDTO result = bakeryCommandService.getBakeryDetail(bakeryId);
    return ApiResponse.onSuccess(result);
  }


}
