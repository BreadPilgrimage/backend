package com.BreadPilgrimage.backend.service.MyPageService;

import com.BreadPilgrimage.backend.web.dto.MypageRequestDTO.InquiryDTO;

public interface MypageCommandService {

  Long createInquiry(long memberId, InquiryDTO inquiryDTO);
}
