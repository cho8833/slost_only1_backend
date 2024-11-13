package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.req.SignUpReq;
import com.slost_only1.slost_only1.enums.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE member SET deleted = true WHERE id = ?")
@SQLRestriction("deleted <> TRUE")
public class Member extends BaseEntity {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private MemberRole role;


    public static Member of(SignUpReq req) {
        return new Member(req.getUsername(), req.getPassword(), req.getPhoneNumber(), MemberRole.PARENT);
    }
}
