package com.BreadPilgrimage.backend.domain;

import com.BreadPilgrimage.backend.domain.common.BaseEntity;
import com.BreadPilgrimage.backend.domain.enums.InquiryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Inquiry extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 제목
  @Column(name = "title", nullable = false)
  private String title;

  // 문의 내용
  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  // 문의 종류
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(20)")
  private InquiryType inquiryType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;
}
