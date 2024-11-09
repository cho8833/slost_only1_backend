package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.ScheduleRepeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE teacher_schedule SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class TeacherSchedule extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomSchedule dolbomSchedule;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @Column
    private ScheduleRepeatType repeatType;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;
}
