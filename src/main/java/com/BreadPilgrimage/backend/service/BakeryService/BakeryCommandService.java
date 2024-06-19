package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import java.util.List;

public interface BakeryCommandService {
  void fetchDataAndSave();

  BakeryResponseDTO.BakeryDetailDTO getBakeryDetail(Long bakeryId);

  List<BakeryMapDTO> getBakeryMap();

  void bookmarkBakery(Long memberId, Long bakeryId);
}
