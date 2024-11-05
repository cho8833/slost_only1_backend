package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DolbomNotice extends BaseEntity {
    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @ManyToOne
    private Member member;

    @ManyToOne
    private DolbomLocation dolbomLocation;


}
