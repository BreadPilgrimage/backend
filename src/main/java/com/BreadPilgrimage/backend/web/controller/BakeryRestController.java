package com.BreadPilgrimage.backend.web.controller;

import com.BreadPilgrimage.backend.service.BakeryService.BakeryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bakeries")
public class BakeryRestController {

  private final BakeryCommandService bakeryCommandService;
}
