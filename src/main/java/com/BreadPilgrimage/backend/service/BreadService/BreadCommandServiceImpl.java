package com.BreadPilgrimage.backend.service.BreadService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.domain.mapping.MemberBakery;
import com.BreadPilgrimage.backend.domain.mapping.MemberBread;
import com.BreadPilgrimage.backend.repository.BakeryRepository;
import com.BreadPilgrimage.backend.repository.BreadRepository;
import com.BreadPilgrimage.backend.repository.MemberBreadRepository;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.BreadRequestDTO.BreadCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BreadCommandServiceImpl implements BreadCommandService {

  private final BreadRepository breadRepository;
  private final BakeryRepository bakeryRepository;
  private final MemberRepository memberRepository;
  private final MemberBreadRepository memberBreadRepository;


  @Override
  public void addBreadLike(long memberId, Long breadId) {
    Bread bread = breadRepository.findById(breadId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BREAD_NOT_FOUND));
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    memberBreadRepository.findByMemberAndBread(member, bread)
        .ifPresent(mb -> { throw new TempHandler(ErrorStatus.ALREADY_LIKED_BREAD); });

    MemberBread memberBread = MemberBread.builder()
        .member(member)
        .bread(bread)
        .build();
    memberBreadRepository.save(memberBread);
  }

  @Override
  public void deleteBreadLike(long memberId, Long breadId) {
    Bread bread = breadRepository.findById(breadId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BREAD_NOT_FOUND));
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    MemberBread memberBread = memberBreadRepository.findByMemberAndBread(member, bread)
        .orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_BOOKMARK));

    memberBreadRepository.delete(memberBread);
  }

  @Override
  public Long breadCreate(Long bakeryId, long memberId, BreadCreateDTO breadCreateDTO, String imageUrl) {
    memberRepository.findById(memberId).orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    Bakery bakery = bakeryRepository.findById(bakeryId).orElseThrow(() -> new TempHandler(ErrorStatus.BAKERY_NOT_FOUND));

    Bread bread = breadRepository.save(Bread.builder()
        .title(breadCreateDTO.getTitle())
        .description(breadCreateDTO.getDescription())
        .price(breadCreateDTO.getPrice())
        .bakery(bakery)
        .image(imageUrl)
        .build());

    return bread.getId();
  }
}
