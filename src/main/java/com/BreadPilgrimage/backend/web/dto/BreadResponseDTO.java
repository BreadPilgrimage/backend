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
    long id;
    String title;
    BigDecimal price;
    long reviewCount;
    long likeCount;
    private String image;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BreadDetailDTO {
    long id;
    String title;
    String description;
    BigDecimal price;
    long reviewCount;
    long likeCount;
    private String image;
  }


  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BreadTop3DTO {
    long id;
    String title;
    long likeCount;
    String bakeryName;
    private String image;
  }
}
