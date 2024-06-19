package com.BreadPilgrimage.backend.domain;

import com.BreadPilgrimage.backend.domain.common.BaseEntity;
import com.BreadPilgrimage.backend.domain.mapping.MemberBread;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class Bread extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 제목
  @Column(name = "title", nullable = false)
  private String title;

  // 설명
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  // 가격
  @Column(name = "price", precision = 19, scale = 4)
  private BigDecimal price;

  // 이미지
  @Column(name = "image", nullable=false)
  private String image;

  // 빵집
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bakery_id", nullable = false)
  private Bakery bakery;

  // 리뷰
  @OneToMany(mappedBy = "bread")
  private List<BreadReview> breadReviews = new ArrayList<>();

  // 좋아요
  @OneToMany(mappedBy = "bread")
  private List<MemberBread> memberBreads = new ArrayList<>();

}
