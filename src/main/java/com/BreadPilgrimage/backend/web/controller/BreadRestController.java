package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.service.BreadService.BreadQueryService;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadPreViewDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/breads")
public class BreadRestController {

  private final BreadQueryService breadQueryService;

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

}
