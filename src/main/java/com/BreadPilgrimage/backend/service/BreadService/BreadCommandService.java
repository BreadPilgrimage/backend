package com.BreadPilgrimage.backend.service.BreadService;

public interface BreadCommandService {

  void addBreadLike(long memberId, Long breadId);
}
