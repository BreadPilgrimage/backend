package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.MemberBakeryRepository;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
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

  @Override
  public BakeryDetailDTO getBakeryDetail(Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));
    long bookmarkCount = memberBakeryRepository.countByBakeryId(bakeryId);
    BakeryDetailDTO bakeryDetailDTO = BakeryDetailDTO.builder()
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
}
