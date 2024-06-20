package com.BreadPilgrimage.backend.service.BakeryService;

import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO;
import com.BreadPilgrimage.backend.web.dto.BakeryResponseDTO.BakeryMapDTO;
import java.util.List;

public interface BakeryQueryService {


  BakeryResponseDTO.BakeryDetailDTO getBakeryDetail(Long bakeryId);

  List<BakeryMapDTO> getBakeryMap();

}
