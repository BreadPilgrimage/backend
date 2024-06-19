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

}
