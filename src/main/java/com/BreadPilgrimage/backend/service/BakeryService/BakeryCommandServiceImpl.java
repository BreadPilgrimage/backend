package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.domain.mapping.MemberBakery;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.MemberBakeryRepository;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.ApiResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryRequestDTO.BakeryDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class BakeryCommandServiceImpl implements BakeryCommandService {

  private final BakeryRepository bakeryRepository;
  private final MemberRepository memberRepository;
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
  public void bookmarkBakery(Long memberId, Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    memberBakeryRepository.findByMemberAndBakery(member, bakery)
        .ifPresent(mb -> { throw new TempHandler(ErrorStatus.BAKERY_ALREADY_BOOKMARK); });

    MemberBakery memberBakery = MemberBakery.builder()
        .member(member)
        .bakery(bakery)
        .build();
    memberBakeryRepository.save(memberBakery);
  }

  @Override
  public void unbookmarkBakery(Long memberId, Long bakeryId) {
    Bakery bakery = bakeryRepository.findById(bakeryId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    MemberBakery memberBakery = memberBakeryRepository.findByMemberAndBakery(member, bakery)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_BOOKMARK));

    memberBakeryRepository.delete(memberBakery);
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
