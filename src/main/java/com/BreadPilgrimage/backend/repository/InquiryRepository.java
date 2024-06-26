package com.BreadPilgrimage.backend.repository;

import com.BreadPilgrimage.backend.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

}
