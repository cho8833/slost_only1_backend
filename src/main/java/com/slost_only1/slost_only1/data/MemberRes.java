package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter

public class MemberRes {

    private Long id;

    private String phoneNumber;

    @QueryProjection
    public MemberRes(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public static MemberRes of(Member member) {
        return new MemberRes(member.getId(), member.getPhoneNumber());
    }

}
