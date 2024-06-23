package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MypageInfoDTO;

public interface MypageQueryService {

  MypageInfoDTO getMypageInfo(long memberId);
}
