package com.slost_only1.slost_only1.model;

import com.slost_only1.slost_only1.base.BaseEntity;
import com.slost_only1.slost_only1.data.SignUpReq;
import com.slost_only1.slost_only1.auth.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Role role;


    public static Member of(SignUpReq req) {
        return new Member(req.getUsername(), req.getPassword(), Role.USER);
    }
}
