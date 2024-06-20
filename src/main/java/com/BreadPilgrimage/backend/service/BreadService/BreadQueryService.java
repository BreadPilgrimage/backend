package com.BreadPilgrimage.backend.service.BreadService;

import com.BreadPilgrimage.backend.web.dto.BreadResponseDTO.BreadPreViewDTO;
import java.util.List;

public interface BreadQueryService {

  List<BreadPreViewDTO> getAllBreadList(Long bakeryId);

  List<BreadPreViewDTO> getTop3Bread(Long bakeryId);
}
