package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.BreadReview;
import com.BreadPilgrimage.backend.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreadReviewRepository extends JpaRepository<BreadReview, Long> {
  long countByBread(Bread bread);

  List<BreadReview> findAllByBreadOrderByCreatedAtDesc(Bread bread);
  List<BreadReview> findAllByMemberOrderByCreatedAtDesc(Member member);
}
