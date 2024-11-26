package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.enums.ReviewReportReason;
import com.slost_only1.slost_only1.model.DolbomReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface DolbomReviewRepository extends JpaRepository<DolbomReview, Long> {

    Optional<DolbomReview> findByDolbomIdAndMemberId(Long id, Long id1);

    Page<DolbomReview> findByTeacherProfileIdAndReportReason(Long id, ReviewReportReason reportReason, Pageable pageable);


}
