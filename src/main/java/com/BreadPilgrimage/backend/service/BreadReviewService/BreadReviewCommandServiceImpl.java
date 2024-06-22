package com.BreadPilgrimage.backend.service.BreadReviewService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.BreadReview;
import com.BreadPilgrimage.backend.domain.BreadReviewImage;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.repository.BreadRepository;
import com.BreadPilgrimage.backend.repository.BreadReviewImageRepository;
import com.BreadPilgrimage.backend.repository.BreadReviewRepository;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.BreadReviewRequestDTO.BreadReviewCreateDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BreadReviewCommandServiceImpl implements BreadReviewCommandService{

  private final MemberRepository memberRepository;
  private final BreadRepository breadRepository;
  private final BreadReviewRepository breadReviewRepository;
  private final BreadReviewImageRepository breadReviewImageRepository;

  @Override
  public Long breadReviewCreate(Long breadId, long memberId,
      BreadReviewCreateDTO breadReviewCreateDTO, List<String> imageUrls) {
    Member member = memberRepository.findById(memberId).orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    Bread bread = breadRepository.findById(breadId).orElseThrow(() -> new TempHandler(ErrorStatus.BREAD_NOT_FOUND));

    BreadReview breadReview = breadReviewRepository.save(BreadReview.builder()
        .member(member)
        .bread(bread)
        .content(breadReviewCreateDTO.getContent())
        .build());

    for (String imageUrl : imageUrls) {
      breadReviewImageRepository.save(BreadReviewImage.builder()
          .imageUrl(imageUrl)
          .breadReview(breadReview)
          .build());
    }

    return breadReview.getId();
  }
}
