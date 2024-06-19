package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.MemberBakeryRepository;
import com.BreadPilgrimage.backend.web.dto.ApiResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryRequestDTO.BakeryDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class BakeryCommandServiceImpl implements BakeryCommandService{

  private final BakeryRepository bakeryRepository;
  private final MemberBakeryRepository memberBakeryRepository;

  private final String apiUrl = "https://www.seogu.go.kr/seoguAPI/3660000/getBakryStts?pageNo=1&numOfRows=192";

  @Override
  public void fetchDataAndSave() {
    RestTemplate restTemplate = new RestTemplate();
    ApiResponseDTO response = restTemplate.getForObject(apiUrl, ApiResponseDTO.class);

    if (response != null && response.getResponse() != null && response.getResponse().getBody() != null) {
      List<BakeryDTO> bakeryDTOs = response.getResponse().getBody().getItems();
      List<Bakery> bakeries = bakeryDTOs.stream()
          .map(this::convertToEntity)
          .collect(Collectors.toList());
      bakeryRepository.saveAll(bakeries);
    }
  }

  @Override
  public BakeryDetailDTO getBakeryDetail(Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_EXIST));
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


  private Bakery convertToEntity(BakeryDTO bakeryDTO) {
    return Bakery.builder()
        .idstyNm(bakeryDTO.getIdstyNm())
        .lo(bakeryDTO.getLo())
        .rnAdrs(bakeryDTO.getRnAdrs())
        .admdCd(bakeryDTO.getAdmdCd())
        .admdNm(bakeryDTO.getAdmdNm())
        .lnmAdrs(bakeryDTO.getLnmAdrs())
        .telno(bakeryDTO.getTelno())
        .la(bakeryDTO.getLa())
        .dataStdrDe(bakeryDTO.getDataStdrDe())
        .sn(bakeryDTO.getSn())
        .bsshNm(bakeryDTO.getBsshNm())
        .lgdngCd(bakeryDTO.getLgdngCd())
        .lgdngNm(bakeryDTO.getLgdngNm())
        .build();
  }



}
