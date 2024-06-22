package com.BreadPilgrimage.backend.service.BreadReviewService;

import com.BreadPilgrimage.backend.web.dto.BreadReviewResponseDTO.BreadReviewDTO;
import java.util.List;

public interface BreadReviewQueryService {

  List<BreadReviewDTO> getBreadReview(Long breadId);
}
