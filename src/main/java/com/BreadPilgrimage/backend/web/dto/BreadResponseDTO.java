package com.BreadPilgrimage.backend.web.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BreadResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BreadPreViewDTO {
    String title;
    BigDecimal price;
    long reviewCount;
    long likeCount;
    private String image;
  }
}
