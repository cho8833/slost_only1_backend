package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import lombok.Getter;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Getter
public class DolbomRes {

    private final Long id;

    private final Set<KidRes> kids;

    private final List<DolbomTimeSlotRes> timeSlots;

    private final Set<DayOfWeek> dayOfWeeks;

    private final TeacherProfileRes teacherProfile;

    private final DolbomLocationRes dolbomLocation;

    private final Time startTime;

    private final Time endTime;

    private final DolbomStatus status;

    private final DolbomCategory category;

    private final Boolean weeklyRepeat;

    private final Boolean setSeveralTime;

    private final LocalDate repeatStartDate;

    private final LocalDate repeatEndDate;

    private final Integer pay;

    @QueryProjection
    public DolbomRes(Long id, Set<KidRes> kids, Set<DolbomTimeSlotRes> timeSlots, Set<DayOfWeek> dows, TeacherProfileRes teacherProfile, DolbomLocationRes dolbomLocation, Time startTime, Time endTime, DolbomStatus status, DolbomCategory category, Boolean weeklyRepeat, Boolean setSeveralTime, LocalDate repeatStartDate, LocalDate repeatEndDate, Integer pay) {
        this.id = id;
        this.kids = kids;
        this.timeSlots = timeSlots.stream().sorted((o1, o2) -> -o2.startDateTime().compareTo(o1.startDateTime())).toList();
        this.dayOfWeeks = dows;
        this.teacherProfile = teacherProfile;
        this.dolbomLocation = dolbomLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.category = category;
        this.weeklyRepeat = weeklyRepeat;
        this.setSeveralTime = setSeveralTime;
        this.repeatStartDate = repeatStartDate;
        this.repeatEndDate = repeatEndDate;
        this.pay = pay;
    }
}
