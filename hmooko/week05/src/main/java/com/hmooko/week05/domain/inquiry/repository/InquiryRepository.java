package com.hmooko.week05.domain.inquiry.repository;

import com.hmooko.week05.domain.inquiry.domain.Inquiry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findAllByUser_Id(Long userId);
}
