package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.service.BakeryService.BakeryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class ApiFetchController {
  private final BakeryCommandService bakeryCommandService;

  @GetMapping("/load")
  public String loadData() {
    bakeryCommandService.fetchDataAndSave();
    return "Data loaded successfully";
  }
}
