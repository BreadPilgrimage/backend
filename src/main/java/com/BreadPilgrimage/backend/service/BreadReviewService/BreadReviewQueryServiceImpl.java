package com.BreadPilgrimage.backend.service.BreadReviewService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.BreadReview;
import com.BreadPilgrimage.backend.domain.BreadReviewImage;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.repository.BreadRepository;
import com.BreadPilgrimage.backend.repository.BreadReviewRepository;
import com.BreadPilgrimage.backend.web.dto.BreadReviewResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BreadReviewResponseDTO.BreadReviewDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BreadReviewQueryServiceImpl implements BreadReviewQueryService {

  private final BreadRepository breadRepository;
  private final BreadReviewRepository breadReviewRepository;

  @Override
  public List<BreadReviewDTO> getBreadReview(Long breadId) {
    Bread bread = breadRepository.findById(breadId).orElseThrow(() -> new TempHandler(ErrorStatus.BREAD_NOT_FOUND));

    List<BreadReview> reviews = breadReviewRepository.findAllByBreadOrderByCreatedAtDesc(bread);
    List<BreadReviewDTO> breadReviewList = new ArrayList<>();

    for(BreadReview review : reviews){
      Member member = review.getMember();
      List<String> imageUrls = review.getBreadReviewImages().stream()
          .map(BreadReviewImage::getImageUrl)
          .toList();

      BreadReviewResponseDTO.BreadReviewDTO breadReviewDTO = BreadReviewDTO.builder()
          .id(review.getId())
          .content(review.getContent())
          .createdAt(review.getCreatedAt())
          .updatedAt(review.getUpdatedAt())
          .images(imageUrls)
          .memberName(member.getName())
          .memberImage(member.getImageUrl())
          .memberId(member.getId())
          .build();

      breadReviewList.add(breadReviewDTO);

    }

    return breadReviewList;
  }
}
