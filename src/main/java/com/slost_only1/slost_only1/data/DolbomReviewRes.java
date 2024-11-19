package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.model.DolbomReview;
import lombok.Getter;

@Getter
public class DolbomReviewRes {
    private final Long id;
    private final String content;
    private final Long star;

    @QueryProjection
    public DolbomReviewRes(Long id, String content, Long star) {
        this.id = id;
        this.content = content;
        this.star = star;
    }

    public static DolbomReviewRes from(DolbomReview review) {
        return new DolbomReviewRes(review.getId(), review.getContent(), review.getStar());
    }
}
