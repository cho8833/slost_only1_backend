package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.model.DolbomReview;
import lombok.Getter;

public record DolbomReviewRes(Long id, String content, Long star) {
    @QueryProjection
    public DolbomReviewRes {
    }

    public static DolbomReviewRes from(DolbomReview review) {
        return new DolbomReviewRes(review.getId(), review.getContent(), review.getStar());
    }
}
