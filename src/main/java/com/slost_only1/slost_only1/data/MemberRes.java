package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.auth.Role;
import com.slost_only1.slost_only1.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRes {

    private final Long id;
    private final String username;
    private final Role role;

    public MemberRes(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public static MemberRes of(Member member) {
        return new MemberRes(member.getId(), member.getUsername(), member.getRole());
    }

}
