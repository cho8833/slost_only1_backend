package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Kid extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Gender gender;

    @Column
    private String tendency;

    @Column
    private String remark;

}
