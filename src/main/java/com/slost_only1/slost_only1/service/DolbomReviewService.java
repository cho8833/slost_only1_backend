package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.DolbomReviewCreateReq;
import com.slost_only1.slost_only1.model.DolbomReview;

import java.util.Optional;

public interface DolbomReviewService {
    Optional<DolbomReview> getReviewByDolbomId(Long dolbomId) throws Throwable;

    DolbomReview create(DolbomReviewCreateReq req);
}