package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.DolbomTimeSlotStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

public record DolbomTimeSlotRes(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                DolbomTimeSlotStatus status) {

    @QueryProjection
    public DolbomTimeSlotRes {
    }
}
