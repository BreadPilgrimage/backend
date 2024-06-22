package com.BreadPilgrimage.backend.web.dto;

import lombok.Builder;
import lombok.Getter;

public class BreadReviewRequestDTO {
  @Getter
  @Builder
  public static class BreadReviewCreateDTO {
    String content;
  }
}
