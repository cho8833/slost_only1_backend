package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE dolbom SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class Dolbom extends BaseEntity {
    @Column
    private LocalDate repeatStartDate;

    @Column
    private LocalDate repeatEndDate;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @Column
    private Boolean weeklyRepeat;

    @Column
    private Boolean setSeveralTime;

    @Column
    private DolbomStatus status;

    @Column
    private String name;

    @Column
    private DolbomCategory category;

    @Column
    private Integer pay;

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomLocation dolbomLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;
}
