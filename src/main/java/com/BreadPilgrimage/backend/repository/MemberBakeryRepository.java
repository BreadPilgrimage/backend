package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.mapping.MemberBakery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBakeryRepository extends JpaRepository<MemberBakery, Long> {
  long countByBakeryId(Long bakeryId);
}
