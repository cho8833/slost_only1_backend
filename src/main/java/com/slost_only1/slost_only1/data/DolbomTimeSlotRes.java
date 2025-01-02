package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.enums.DolbomTimeSlotStatus;
import com.slost_only1.slost_only1.model.DolbomTimeSlot;

import java.time.LocalDateTime;

public record DolbomTimeSlotRes(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime,
                                DolbomTimeSlotStatus status) {

    public static DolbomTimeSlotRes from(DolbomTimeSlot data) {
        return new DolbomTimeSlotRes(data.getId(), data.getStartDateTime(), data.getEndDateTime(), data.getStatus());
    }
}
