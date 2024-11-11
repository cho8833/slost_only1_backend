package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.DolbomTimeSlotStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DolbomTimeSlotRes {

    private final Long id;

    private final LocalDateTime startDateTime;

    private final LocalDateTime endDateTime;

    private final DolbomTimeSlotStatus status;


    @QueryProjection
    public DolbomTimeSlotRes(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime, DolbomTimeSlotStatus status) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
    }
}
