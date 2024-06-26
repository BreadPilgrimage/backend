package com.BreadPilgrimage.backend.web.dto;

import com.BreadPilgrimage.backend.domain.enums.InquiryType;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

public class MypageRequestDTO {
  @Getter
  @Builder
  public static class InquiryDTO {
    String title;
    String content;
    InquiryType inquiryType;
  }
}
