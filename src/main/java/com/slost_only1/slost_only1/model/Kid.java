package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.req.KidCreateReq;
import com.slost_only1.slost_only1.data.req.KidUpdateReq;
import com.slost_only1.slost_only1.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@SQLDelete(sql = "UPDATE kid SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class Kid extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column
    private String name;

    @Column
    private LocalDateTime birthday;

    @Column
    private Gender gender;

    @Column
    private String tendency;

    @Column
    private String remark;

    public static Kid from(KidCreateReq req, Member member) {
        return new Kid(member, req.getName(), req.getBirthday(), req.getGender(), req.getTendency(), req.getRemark());
    }

    public void copy(KidUpdateReq req) {
        setName(req.getName());
        setGender(req.getGender());
        setBirthday(req.getBirthday());
        setTendency(req.getTendency());
        setRemark(req.getRemark());
    }

}
