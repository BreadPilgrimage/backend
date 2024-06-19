package com.BreadPilgrimage.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BakeryResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BakeryDetailDTO {
    private String idstyNm;
    private String bsshNm;
    private String lnmAdrs;
    private String rnAdrs;
    private String admdNm;
    private String lgdngNm;
    private String telno;
    private long bookmarks;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BakeryMapDTO {
    private Long id;
    private double la; //위도
    private double lo; //경도
    private String bsshNm; //가게명
  }

}
