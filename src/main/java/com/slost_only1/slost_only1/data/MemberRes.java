package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.Member;
import lombok.Getter;

public record MemberRes(
        Long id,
        String phoneNumber,
        MemberRole role,
        String sendbirdAccessToken) {

    @QueryProjection
    public MemberRes {
    }

    public static MemberRes of(Member member) {
        return new MemberRes(member.getId(), member.getPhoneNumber(), member.getRole(), member.getSendbirdAccessToken());
    }

}
