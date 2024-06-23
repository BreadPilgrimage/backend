package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MyBreadReviewDTO;
import com.BreadPilgrimage.backend.web.dto.MypageResponseDTO.MypageInfoDTO;
import java.util.List;

public interface MypageQueryService {

  MypageInfoDTO getMypageInfo(long memberId);

  List<MyBreadReviewDTO> getMyBreadReviews(long memberId);
}
