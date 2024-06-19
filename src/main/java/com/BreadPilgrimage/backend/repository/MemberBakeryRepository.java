package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Bakery;
import com.BreadPilgrimage.backend.domain.Member;
import com.BreadPilgrimage.backend.domain.mapping.MemberBakery;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBakeryRepository extends JpaRepository<MemberBakery, Long> {
  long countByBakeryId(Long bakeryId);

  Optional<MemberBakery> findByMemberAndBakery(Member member, Bakery bakery);
}
