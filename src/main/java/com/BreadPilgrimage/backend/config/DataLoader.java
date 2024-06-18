package com.BreadPilgrimage.backend.config;

import com.BreadPilgrimage.backend.service.BakeryService.BakeryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final BakeryCommandService bakeryCommandService;

  @Override
  public void run(String... args) throws Exception {
    bakeryCommandService.fetchDataAndSave();
  }
}