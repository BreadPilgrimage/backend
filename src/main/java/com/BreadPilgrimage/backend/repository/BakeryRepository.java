package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Bakery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BakeryRepository extends JpaRepository<Bakery, Long> {

  List<Bakery> findByBsshNmContains(String bakeryName);
}
