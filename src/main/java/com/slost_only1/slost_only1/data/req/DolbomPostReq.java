package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.enums.DolbomCategory;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DolbomPostReq {
    Long dolbomLocationId;
    List<Long> kidIds;
    Integer pay;
    List<DolbomTimeSlotCreateReq> timeSlots;
    DolbomCategory category;

    Boolean weeklyRepeat;
    List<DayOfWeek> dows;           // isWeeklyRepeat == false, null
    LocalDateTime repeatStartDate;  // isWeeklyRepeat == false, null
    LocalDateTime repeatEndDate;    // isWeeklyRepeat == false, null

    Boolean setSeveralTime;
    LocalDateTime startTime;        // setSeveralTime == true, null
    LocalDateTime endTime;          // setSeveralTime == true, null


    @Getter
    public static class DolbomTimeSlotCreateReq {
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
    }
}
