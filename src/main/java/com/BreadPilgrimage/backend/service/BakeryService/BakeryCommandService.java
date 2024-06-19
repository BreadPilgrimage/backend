package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;

public interface BakeryCommandService {
  void fetchDataAndSave();

  BakeryResponseDTO.BakeryDetailDTO getBakeryDetail(Long bakeryId);
}
