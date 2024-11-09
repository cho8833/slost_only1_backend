package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dolbom SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class Dolbom extends BaseEntity {
    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    private Integer pay;

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomLocation dolbomLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomNotice dolbomNotice;

    @Column
    private DolbomCategory category;

}
