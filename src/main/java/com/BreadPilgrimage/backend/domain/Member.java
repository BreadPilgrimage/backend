package com.BreadPilgrimage.backend.domain;

import com.BreadPilgrimage.backend.domain.common.BaseEntity;
import com.BreadPilgrimage.backend.domain.enums.MemberStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, length = 20)
  private String nickname;

  @Column(nullable = false, length = 20, unique = true)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(columnDefinition = "TEXT")
  private String bio;

  @Column(nullable = false)
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
  private MemberStatus status;

  private LocalDate inactiveDate;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Inquiry> inquiries = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<BreadReview> breadReviews = new ArrayList<>();

}