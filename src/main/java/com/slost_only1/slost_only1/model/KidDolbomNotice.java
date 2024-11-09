package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
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
@SQLDelete(sql = "UPDATE kid_dolbom_notice SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class KidDolbomNotice extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private DolbomNotice dolbomNotice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Kid kid;
}
