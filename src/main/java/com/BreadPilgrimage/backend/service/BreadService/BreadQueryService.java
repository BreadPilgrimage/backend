package com.BreadPilgrimage.backend.service.BreadService;

import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadDetailDTO;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadPreViewDTO;
import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadTop3DTO;
import java.util.List;

public interface BreadQueryService {

  List<BreadPreViewDTO> getAllBreadList(Long bakeryId);

  List<BreadPreViewDTO> getTop3Bread(Long bakeryId);

  BreadDetailDTO getBreadDetail(Long breadId);

  List<BreadTop3DTO> getTotalTop3Bread();

  Boolean checkLikeStatus(Long breadId, Long memberId);
}
