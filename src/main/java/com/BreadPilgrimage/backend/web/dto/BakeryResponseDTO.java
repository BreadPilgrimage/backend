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
  public static class BakeryPreViewDTO {
    private int sn;
    private String idstyNm;
    private String bsshNm;
    private String lnmAdrs;
    private String rnAdrs;
    private String admdCd;
    private String admdNm;
    private String lgdngCd;
    private String lgdngNm;
    private String telno;
    private double la;
    private double lo;
    private String dataStdrDe;
  }

}
