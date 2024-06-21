package com.BreadPilgrimage.backend.service.BreadService;

import com.BreadPilgrimage.backend.web.dto.BreadRequestDTO;
import java.util.List;

public interface BreadCommandService {

  void addBreadLike(long memberId, Long breadId);

  void deleteBreadLike(long memberId, Long breadId);

  Long breadCreate(Long bakeryId, long memberId, BreadRequestDTO.BreadCreateDTO breadCreateDTO, String imageUrl);
}
