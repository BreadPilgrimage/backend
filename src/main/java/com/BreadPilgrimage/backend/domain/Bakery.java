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

  // 순번
  @Column(nullable = false)
  private int sn;

  // 업종명
  @Column(length = 50, nullable = false)
  private String idstyNm;

  //업소명
  @Column(length = 50, nullable = false)
  private String bsshNm;

  //지번주소
  @Column(length = 100, nullable = false)
  private String lnmAdrs;

  //도로명주소
  @Column(length = 100, nullable = false)
  private String rnAdrs;

  //행정동코드
  @Column(length = 10, nullable = false)
  private String admdCd;

  //행정동명
  @Column(length = 10, nullable = false)
  private String admdNm;

  //법정동코드
  @Column(length = 10, nullable = false)
  private String lgdngCd;

  //법정동명
  @Column(length = 10, nullable = false)
  private String lgdngNm;

  //전화번호
  @Column(length = 30, nullable = false)
  private String telno;

  //데이터기준일자
  @Column(length = 10, nullable = false)
  private String dataStdrDe;

//  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
//  @JsonBackReference
//  private List<Review> reviewList = new ArrayList<>();
}