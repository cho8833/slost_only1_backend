package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomTimeSlotStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
@SQLDelete(sql = "UPDATE dolbom_time_slot SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class DolbomTimeSlot extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Dolbom dolbom;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    private DolbomTimeSlotStatus status;

    public static DolbomTimeSlot from(DolbomPostReq.DolbomTimeSlotCreateReq req, Dolbom dolbom) {
        return new DolbomTimeSlot(dolbom, req.getStartDateTime(), req.getEndDateTime(), DolbomTimeSlotStatus.RESERVED);
    }
}
