package com.BreadPilgrimage.backend.service.BakeryService;


public interface BakeryCommandService {
  void fetchDataAndSave();

  void bookmarkBakery(Long memberId, Long bakeryId);

  void unbookmarkBakery(Long memberId, Long bakeryId);
}
