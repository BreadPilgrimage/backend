package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.BreadReview;
import com.BreadPilgrimage.backend.domain.BreadReviewImage;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.repository.BreadReviewRepository;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MyBreadReviewDTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MypageInfoDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageQueryServiceImpl implements MypageQueryService{

  private final MemberRepository memberRepository;
  private final BreadReviewRepository breadReviewRepository;


  @Override
  public MypageInfoDTO getMypageInfo(long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));

    return MypageInfoDTO.builder()
        .id(member.getId())
        .name(member.getName())
        .nickname(member.getNickname())
        .email(member.getEmail())
        .bio(member.getBio())
        .imageUrl(member.getImageUrl())
        .reviewCount(member.getBreadReviews().stream().count())
        .build();
  }

  @Override
  public List<MyBreadReviewDTO> getMyBreadReviews(long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));

    List<BreadReview> reviews = breadReviewRepository.findAllByMemberOrderByCreatedAtDesc(member);

    List<MyBreadReviewDTO> reviewDTOs = reviews.stream()
        .map(review -> {
          List<BreadReviewImage> images = review.getBreadReviewImages(); // 빵 리뷰 이미지 리스트 가져오기

          // 이미지 리스트가 비어있는 경우 기본 이미지 URL 설정
          String imageUrl = "https://baconmockup.com/250/250/";
          if (images != null && !images.isEmpty()) {
            imageUrl = images.get(0).getImageUrl(); // 첫 번째 이미지 URL 가져오기
          }

          return MyBreadReviewDTO.builder()
              .reviewId(review.getId())
              .content(review.getContent())
              .createdAt(review.getCreatedAt())
              .updatedAt(review.getUpdatedAt())
              .imageUrl(imageUrl)
              .build();
        })
        .collect(Collectors.toList());

    return reviewDTOs;
  }

}
