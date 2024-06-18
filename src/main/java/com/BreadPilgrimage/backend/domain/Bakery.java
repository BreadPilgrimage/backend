package com.BreadPilgrimage.backend.domain;

import com.BreadPilgrimage.backend.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Bakery extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "idsty_nm")
  private String idstyNm;

  @Column(name = "lo")
  private double lo;

  @Column(name = "rn_adrs")
  private String rnAdrs;

  @Column(name = "admd_cd")
  private String admdCd;

  @Column(name = "admd_nm")
  private String admdNm;

  @Column(name = "lnm_adrs")
  private String lnmAdrs;

  @Column(name = "telno")
  private String telno;

  @Column(name = "la")
  private double la;

  @Column(name = "data_stdr_de")
  private String dataStdrDe;

  @Column(name = "sn")
  private int sn;

  @Column(name = "bssh_nm")
  private String bsshNm;

  @Column(name = "lgdng_cd")
  private String lgdngCd;

  @Column(name = "lgdng_nm")
  private String lgdngNm;



//  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
//  @JsonBackReference
//  private List<Review> reviewList = new ArrayList<>();
}