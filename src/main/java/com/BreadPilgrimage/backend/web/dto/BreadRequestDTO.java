package com.BreadPilgrimage.backend.web.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

public class BreadRequestDTO {
  @Getter
  @Builder
  public static class BreadCreateDTO {

    String title;
    String description;
    BigDecimal price;
  }
}
