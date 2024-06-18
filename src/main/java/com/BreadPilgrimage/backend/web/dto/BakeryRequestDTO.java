package com.BreadPilgrimage.backend.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class BakeryRequestDTO {

  @Getter
  public static class BakeryDTO {

    @JsonProperty("idsty_nm")
    private String idstyNm;

    @JsonProperty("lo")
    private double lo;

    @JsonProperty("rn_adrs")
    private String rnAdrs;

    @JsonProperty("admd_cd")
    private String admdCd;

    @JsonProperty("admd_nm")
    private String admdNm;

    @JsonProperty("lnm_adrs")
    private String lnmAdrs;

    @JsonProperty("telno")
    private String telno;

    @JsonProperty("la")
    private double la;

    @JsonProperty("data_stdr_de")
    private String dataStdrDe;

    @JsonProperty("sn")
    private int sn;

    @JsonProperty("bssh_nm")
    private String bsshNm;

    @JsonProperty("lgdng_cd")
    private String lgdngCd;

    @JsonProperty("lgdng_nm")
    private String lgdngNm;

  }
}
