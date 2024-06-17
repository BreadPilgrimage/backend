package com.BreadPilgrimage.backend.service.BakeryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BakeryCommandServiceImpl implements BakeryCommandService{
}
