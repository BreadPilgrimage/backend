package com.BreadPilgrimage.backend.web.dto;

import com.BreadPilgrimage.backend.domain.enums.MemberStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MypageResponseDTO {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MypageInfoDTO{
    Long id;
    String name;
    String nickname;
    String email;
    String bio;
    String imageUrl;
    long reviewCount;
  }
}
