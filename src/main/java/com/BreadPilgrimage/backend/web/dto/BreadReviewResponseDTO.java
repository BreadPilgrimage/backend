package com.BreadPilgrimage.backend.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BreadReviewResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BreadReviewDTO {
    long id;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    private List<String> images;
    String memberName;
    String memberImage;
    long memberId;
  }

}
