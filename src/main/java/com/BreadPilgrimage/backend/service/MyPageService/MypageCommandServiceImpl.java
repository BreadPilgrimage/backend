package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Inquiry;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.repository.InquiryRepository;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.MypageRequestDTO.InquiryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageCommandServiceImpl implements MypageCommandService{

  private final MemberRepository memberRepository;
  private final InquiryRepository inquiryRepository;

  @Override
  public Long createInquiry(long memberId, InquiryDTO inquiryDTO) {
    Member member = memberRepository.findById(memberId).orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
    Inquiry inquiry = inquiryRepository.save(Inquiry.builder()
        .title(inquiryDTO.getTitle())
        .content(inquiryDTO.getContent())
        .inquiryType(inquiryDTO.getInquiryType())
        .member(member)
        .build());
    return inquiry.getId();
  }
}
