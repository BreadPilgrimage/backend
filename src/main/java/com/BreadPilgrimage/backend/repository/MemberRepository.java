package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);

  Optional<Member> findByEmail (String email);
}
