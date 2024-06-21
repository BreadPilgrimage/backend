package com.BreadPilgrimage.backend.web.dto;

import java.math.BigDecimal;
import lombok.Getter;

public class BreadRequestDTO {
  @Getter
  public static class BreadCreateDTO {

    String title;
    String description;
    BigDecimal price;
  }
}
