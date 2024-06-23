package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.apiPayload.code.status.ErrorStatus;
import com.BreadPilgrimage.backend.apiPayload.exception.handler.TempHandler;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MypageInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageQueryServiceImpl implements MypageQueryService{

  private final MemberRepository memberRepository;

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
}
