package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.model.AvailableArea;

public record AvailableAreaRes(Long id, String sido, String sigungu) {

    @QueryProjection
    public AvailableAreaRes {

    }

    public static AvailableAreaRes from(AvailableArea area) {
        return new AvailableAreaRes(area.getId(), area.getSido(), area.getSigungu());
    }
}
