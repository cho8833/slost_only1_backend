package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.req.DolbomLocationCreateReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE dolbom_location SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class DolbomLocation extends BaseEntity {

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column
    private String name;


    public static DolbomLocation from(DolbomLocationCreateReq req, Member member) {
        return new DolbomLocation(
                req.getAddress(), member, req.getName()
        );
    }
}
