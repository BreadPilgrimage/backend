package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Bread;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.domain.mapping.MemberBread;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBreadRepository extends JpaRepository<MemberBread, Long> {
  long countByBread(Bread bread);

  Optional<MemberBread> findByMemberAndBread(Member member, Bread bread);
}
