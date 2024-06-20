package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.BreadReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadReviewRepository extends JpaRepository<BreadReview, Long> {
  long countByBread(Bread bread);
}
