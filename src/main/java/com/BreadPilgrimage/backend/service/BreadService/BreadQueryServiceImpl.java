package com.BreadPilgrimage.backend.service.BreadService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.BreadRepository;
import com.BreadPilgrimage.backend.repository.BreadReviewRepository;
import com.BreadPilgrimage.backend.repository.MemberBreadRepository;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadPreViewDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BreadQueryServiceImpl implements BreadQueryService{

  private final BreadRepository breadRepository;
  private final BakeryRepository bakeryRepository;
  private final BreadReviewRepository breadReviewRepository;
  private final MemberBreadRepository memberBreadRepository;

  @Override
  public List<BreadPreViewDTO> getAllBreadList(Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));

    List<Bread> breadList = breadRepository.findByBakery(bakery);

    List<BreadPreViewDTO> breadPreViewDTOList = breadList.stream()
        .map(bread -> {
          long reviewCount = breadReviewRepository.countByBread(bread);
          long likeCount = memberBreadRepository.countByBread(bread);

          return BreadPreViewDTO.builder()
              .id(bread.getId())
              .title(bread.getTitle())
              .price(bread.getPrice())
              .reviewCount(reviewCount)
              .likeCount(likeCount)
              .image(bread.getImage())
              .build();
        })
        .collect(Collectors.toList());

    return breadPreViewDTOList;
  }

  @Override
  public List<BreadPreViewDTO> getTop3Bread(Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));

    List<Bread> breadList = breadRepository.findByBakery(bakery);

    List<Bread> sortedBreadList = breadList.stream()
        .sorted(Comparator.comparingLong(bread ->
            memberBreadRepository.countByBread((Bread) bread)).reversed())
        .limit(3)
        .collect(Collectors.toList());

    List<BreadPreViewDTO> top3BreadPreViewDTOList = sortedBreadList.stream()
        .map(bread -> {
          long reviewCount = bread.getBreadReviews().size(); // Assuming getBreadReviews() returns List<BreadReview>
          long likeCount = memberBreadRepository.countByBread(bread);
          return BreadPreViewDTO.builder()
              .id(bread.getId())
              .title(bread.getTitle())
              .price(bread.getPrice())
              .reviewCount(reviewCount)
              .likeCount(likeCount)
              .image(bread.getImage())
              .build();
        })
        .collect(Collectors.toList());

    return top3BreadPreViewDTOList;
  }

  @Override
  public BreadDetailDTO getBreadDetail(Long breadId) {
    Bread bread = breadRepository.findById(breadId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BREAD_NOT_FOUND));
    long reviewCount = breadReviewRepository.countByBread(bread);
    long likeCount = memberBreadRepository.countByBread(bread);
    BreadDetailDTO breadDetailDTO = BreadDetailDTO.builder()
        .id(bread.getId())
        .title(bread.getTitle())
        .description(bread.getDescription())
        .price(bread.getPrice())
        .reviewCount(reviewCount)
        .likeCount(likeCount)
        .image(bread.getImage())
        .build();

    return breadDetailDTO;
  }


}
