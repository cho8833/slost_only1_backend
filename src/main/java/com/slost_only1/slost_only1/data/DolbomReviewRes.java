package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.ReviewReportReason;
import com.slost_only1.slost_only1.model.DolbomReview;
import lombok.Getter;

public record DolbomReviewRes(Long id, String content, Long star, Long dolbomId, String reportContent,
                              ReviewReportReason reportReason) {
    @QueryProjection
    public DolbomReviewRes {
    }

    public static DolbomReviewRes from(DolbomReview review) {
        return new DolbomReviewRes(review.getId(), review.getContent(), review.getStar(), review.getDolbom().getId(), review.getReportContent(), review.getReportReason());
    }
}
