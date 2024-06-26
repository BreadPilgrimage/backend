package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.BreadReview;
import com.BreadPilgrimage.backend.domain.BreadReviewImage;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.domain.mapping.MemberBakery;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.BreadRepository;
import com.BreadPilgrimage.backend.repository.BreadReviewRepository;
import com.BreadPilgrimage.backend.repository.MemberBakeryRepository;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryTop3DTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MyBakeryDTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MyBreadReviewDTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MypageInfoDTO;
import java.util.Comparator;
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
  private final BreadRepository breadRepository;
  private final MemberBakeryRepository memberBakeryRepository;


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
              .breadId(review.getBread().getId())
              .content(review.getContent())
              .createdAt(review.getCreatedAt())
              .updatedAt(review.getUpdatedAt())
              .imageUrl(imageUrl)
              .build();
        })
        .collect(Collectors.toList());

    return reviewDTOs;
  }

  @Override
  public List<MyBakeryDTO> getMyBakeries(long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));

    List<MemberBakery> memberBakeries = memberBakeryRepository.findAllByMemberOrderByCreatedAtDesc(member);

    List<MyBakeryDTO> myBakeryDTOS = memberBakeries.stream()
        .map(memberBakery -> {
          Bakery bakery = memberBakery.getBakery();

          long bookmarkCount = memberBakeryRepository.countByBakeryId(bakery.getId());
          List<Bread> breads = breadRepository.findByBakery(bakery);

          String imageUrl;
          // 빵집에서 가장 인기 많은 빵 사진을 대표 사진으로 설정
          if (breads.isEmpty()) {
            imageUrl = "https://baconmockup.com/250/250/"; // TODO: 기본이미지 나중에 수정하기
          } else {
            imageUrl = breads.stream()
                .max(Comparator.comparingLong(bread -> bread.getMemberBreads().size()))
                .map(Bread::getImage)
                .orElse("https://baconmockup.com/250/250/"); // TODO: 기본이미지 나중에 수정하기
          }

          return new MyBakeryDTO(
              bakery.getId(),
              bakery.getBsshNm(),
              bakery.getRnAdrs(),
              bookmarkCount,
              imageUrl
          );
        })
        .collect(Collectors.toList());

    return myBakeryDTOS;
  }


}
