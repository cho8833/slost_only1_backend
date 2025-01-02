package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class DolbomRes {

    private final Long id;

    private final List<KidRes> kids;

    private final List<DolbomTimeSlotRes> timeSlots;

    private final List<DayOfWeek> dayOfWeeks;

    private final DolbomLocationRes dolbomLocation;

    private final Long teacherProfileId;

    private final Time startTime;

    private final Time endTime;

    private final DolbomStatus status;

    private final DolbomCategory category;

    private final Boolean weeklyRepeat;

    private final Boolean setSeveralTime;

    private final LocalDate repeatStartDate;

    private final LocalDate repeatEndDate;

    private final Integer pay;
}
