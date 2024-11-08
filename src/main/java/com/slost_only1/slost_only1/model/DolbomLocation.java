package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import jakarta.persistence.Embedded;
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
@SQLDelete(sql = "UPDATE dolbom_location SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class DolbomLocation extends BaseEntity {

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
