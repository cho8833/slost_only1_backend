package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.DolbomReviewCreateReq;
import com.slost_only1.slost_only1.data.req.ReviewReportReq;
import com.slost_only1.slost_only1.model.DolbomReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DolbomReviewService {
    Optional<DolbomReview> getReviewByDolbomId(Long dolbomId) throws Throwable;

    DolbomReview create(DolbomReviewCreateReq req);

    void report(ReviewReportReq req);

    Page<DolbomReview> getReported(Pageable pageable);
}
