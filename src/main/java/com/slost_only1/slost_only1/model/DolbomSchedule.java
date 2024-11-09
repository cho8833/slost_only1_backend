package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.ScheduleRepeatType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
@SQLDelete(sql = "UPDATE dolbom_schedule SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class DolbomSchedule extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Dolbom dolbom;

    @Column
    private ScheduleRepeatType repeatType;

    @Column
    private Time startTime;

    @Column
    private Long dolbomHour;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;
}
