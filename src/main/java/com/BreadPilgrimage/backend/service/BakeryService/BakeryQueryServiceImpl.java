package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.BreadRepository;
import com.BreadPilgrimage.backend.repository.MemberBakeryRepository;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryTop3DTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BakeryQueryServiceImpl implements BakeryQueryService{

  private final BakeryRepository bakeryRepository;
  private final MemberBakeryRepository memberBakeryRepository;
  private final BreadRepository breadRepository;

  @Override
  public BakeryDetailDTO getBakeryDetail(Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));
    long bookmarkCount = memberBakeryRepository.countByBakeryId(bakeryId);
    BakeryDetailDTO bakeryDetailDTO = BakeryDetailDTO.builder()
        .id(bakery.getId())
        .idstyNm(bakery.getIdstyNm())
        .admdNm(bakery.getAdmdNm())
        .bsshNm(bakery.getBsshNm())
        .lgdngNm(bakery.getLgdngNm())
        .lnmAdrs(bakery.getLnmAdrs())
        .rnAdrs(bakery.getRnAdrs())
        .telno(bakery.getTelno())
        .bookmarks(bookmarkCount)
        .build();

    return bakeryDetailDTO;
  }

  @Override
  public List<BakeryMapDTO> getBakeryMap() {
    List<Bakery> bakeries = bakeryRepository.findAll();
    return bakeries.stream()
        .map(bakery -> BakeryMapDTO.builder()
            .id(bakery.getId())
            .la(bakery.getLa())
            .lo(bakery.getLo())
            .bsshNm(bakery.getBsshNm())
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<BakeryTop3DTO> getTotalTop3Bakery() {
    List<Bakery> bakeryList = bakeryRepository.findAll();

    List<BakeryTop3DTO> top3Bakeries = bakeryList.stream()
        .map(bakery -> {
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

          return new BakeryTop3DTO(
              bakery.getId(),
              bakery.getBsshNm(),
              bakery.getRnAdrs(),
              bookmarkCount,
              imageUrl
          );
        })
        .sorted(Comparator.comparingLong(BakeryTop3DTO::getBookmarks).reversed())
        .limit(3)
        .collect(Collectors.toList());

    return top3Bakeries;
  }



}
