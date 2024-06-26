package com.BreadPilgrimage.backend.web.dto;

import java.time.LocalDateTime;
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

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MyBreadReviewDTO {
    long reviewId;
    long breadId;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String imageUrl;}

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MyBakeryDTO {
    long id;
    String bsshNm;
    String rnAdrs;
    long bookmarks;
    String imageUrl;}

}

