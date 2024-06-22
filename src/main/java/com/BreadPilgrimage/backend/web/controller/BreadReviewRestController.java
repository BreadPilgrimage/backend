package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.apiPayload.ApiResponse;
import com.BreadPilgrimage.backend.service.BreadReviewService.BreadReviewCommandService;
import com.BreadPilgrimage.backend.service.S3Service.S3Uploader;
import com.BreadPilgrimage.backend.web.dto.BreadRequestDTO;
import com.BreadPilgrimage.backend.web.dto.BreadReviewRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bread_reviews")
public class BreadReviewRestController {

  private final BreadReviewCommandService breadReviewCommandService;
  private final S3Uploader s3Uploader;

  // 리뷰 작성
  @PostMapping(value ="/{breadId}", consumes = "multipart/form-data")
  @Operation(summary = "빵 리뷰 작성 API", description = "빵 리뷰 작성 API입니다. 빵 아이디(breadId) PathVariable 입니다!")
  public ApiResponse<Long> breadReviewCreate(@PathVariable("breadId") Long breadId, @AuthenticationPrincipal String memberId, @ModelAttribute BreadReviewRequestDTO.BreadReviewCreateDTO breadReviewCreateDTO, @RequestPart(value = "images", required = false) List<MultipartFile> images) {
    String dirName = "bread_review";
    List<String> imageUrls = s3Uploader.uploadFiles(dirName, images); // 이미지 업로드

    Long reviewId = breadReviewCommandService.breadReviewCreate(breadId, Long.parseLong(memberId),breadReviewCreateDTO,imageUrls);
    return ApiResponse.onSuccess(reviewId);
  }
}
