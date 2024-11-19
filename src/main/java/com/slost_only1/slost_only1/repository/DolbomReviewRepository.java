package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.DolbomReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DolbomReviewRepository extends JpaRepository<DolbomReview, Long> {

    Optional<DolbomReview> findByDolbomIdAndMemberId(Long id, Long id1);


}
