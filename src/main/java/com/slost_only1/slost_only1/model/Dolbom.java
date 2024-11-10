package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dolbom SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class Dolbom extends BaseEntity {
    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @Column
    private Boolean weeklyRepeat;

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

}
