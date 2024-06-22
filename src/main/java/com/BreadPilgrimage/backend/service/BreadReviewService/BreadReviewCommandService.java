package com.BreadPilgrimage.backend.service.BreadReviewService;

import com.BreadPilgrimage.backend.web.dto.BreadReviewRequestDTO.BreadReviewCreateDTO;
import java.util.List;

public interface BreadReviewCommandService {

  Long breadReviewCreate(Long breadId, long memberId, BreadReviewCreateDTO breadReviewCreateDTO, List<String> imageUrls);
}
