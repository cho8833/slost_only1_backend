package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.DolbomStatus;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public record DolbomRes(Long id, List<KidRes> kids, List<DolbomTimeSlotRes> timeSlots, List<DayOfWeek> dayOfWeeks,
                        DolbomLocationRes dolbomLocation, Long teacherProfileId, Time startTime, Time endTime,
                        DolbomStatus status, DolbomCategory category, Boolean weeklyRepeat, Boolean setSeveralTime,
                        LocalDate repeatStartDate, LocalDate repeatEndDate, Integer pay) {

}
