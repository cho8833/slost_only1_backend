package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.model.DolbomReview;

public interface DolbomReviewService {
    DolbomReview getReviewByDolbomId(Long dolbomId) throws Throwable;
}
