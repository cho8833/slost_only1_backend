package com.slost_only1.slost_only1.model;


import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.req.AvailableAreaCreateReq;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE available_area SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class AvailableArea extends BaseEntity {

    @Column
    private String sido;

    @Column
    private String sigungu;

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;


    public static AvailableArea from(AvailableAreaCreateReq req, TeacherProfile teacherProfile) {
        return new AvailableArea(req.sido(), req.sigungu(), teacherProfile);
    }
}
