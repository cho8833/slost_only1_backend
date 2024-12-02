package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AvailableCategory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherProfile teacherProfile;

    @Column
    private DolbomCategory category;

}
