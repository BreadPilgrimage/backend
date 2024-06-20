package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import java.util.List;

public interface BakeryCommandService {
  void fetchDataAndSave();

  void bookmarkBakery(Long memberId, Long bakeryId);

  void unbookmarkBakery(Long memberId, Long bakeryId);
}
