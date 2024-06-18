package com.BreadPilgrimage.backend.service.MemberService;

import com.BreadPilgrimage.backend.web.dto.MemberRequestDTO;
import com.BreadPilgrimage.backend.web.dto.MemberResponseDTO;

public interface MemberCommandService {

  MemberResponseDTO.JoinResultDTO joinMember(MemberRequestDTO.JoinDTO request);
}
