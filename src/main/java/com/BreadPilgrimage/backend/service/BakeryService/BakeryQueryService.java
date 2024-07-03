package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryTop3DTO;
import java.util.List;

public interface BakeryQueryService {


  BakeryResponseDTO.BakeryDetailDTO getBakeryDetail(Long bakeryId);

  List<BakeryMapDTO> getBakeryMap();

  List<BakeryTop3DTO> getTotalTop3Bakery();

  Boolean checkBookmarkStatus(Long bakeryId, Long memberId);

  List<BakeryMapDTO> searchMapBakery(String bakeryName);

  List<BakeryResponseDTO.BakeryDetailDTO> searchBakery(String bakeryName);
}
