package com.BreadPilgrimage.backend.service.MemberService;

import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.repository.MemberRepository;
import com.BreadPilgrimage.backend.web.dto.MemberRequestDTO;
import com.BreadPilgrimage.backend.web.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  private static final String DEFAULT_PROFILE_IMAGE_URL = "https://baconmockup.com/250/250/";

  @Override
  public MemberResponseDTO.JoinResultDTO joinMember(MemberRequestDTO.JoinDTO request)  {

    Member member = Member.builder()
        .name(request.getName())
        .nickname(request.getNickname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .bio("소개글을 작성해주세요.")
        .imageUrl(DEFAULT_PROFILE_IMAGE_URL)
        .build();

    return MemberResponseDTO.JoinResultDTO.builder().memberId(memberRepository.save(member).getId()).build();
  }

}
