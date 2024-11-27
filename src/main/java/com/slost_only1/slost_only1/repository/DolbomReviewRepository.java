package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.DolbomReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DolbomReviewRepository extends JpaRepository<DolbomReview, Long> {

    Optional<DolbomReview> findByDolbomIdAndMemberId(Long id, Long id1);

    Page<DolbomReview> findByTeacherProfile_IdAndReportReasonNull(Long id, Pageable pageable);

    Page<DolbomReview> findByTeacherProfile_Id(Long id, Pageable pageable);


}
