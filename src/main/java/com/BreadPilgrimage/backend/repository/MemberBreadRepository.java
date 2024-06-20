package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.mapping.MemberBread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBreadRepository extends JpaRepository<MemberBread, Long> {
  long countByBread(Bread bread);
}
