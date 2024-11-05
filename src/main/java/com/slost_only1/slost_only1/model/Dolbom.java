package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dolbom extends BaseEntity {
    @Column
    private DateTime startDateTime;

    @Column
    private DateTime endDateTime;

    @Column
    private Integer pay;

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomLocation dolbomLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Kid kid;

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomNotice dolbomNotice;

}
