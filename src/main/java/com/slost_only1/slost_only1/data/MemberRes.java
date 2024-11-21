package com.slost_only1.slost_only1.data;

import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter

public class MemberRes {

    private Long id;

    private String phoneNumber;

    private MemberRole role;

    private String sendbirdAccessToken;

    @QueryProjection
    public MemberRes(Long id, String phoneNumber, MemberRole role, String sendbirdAccessToken) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.sendbirdAccessToken = sendbirdAccessToken;
    }

    public static MemberRes of(Member member) {
        return new MemberRes(member.getId(), member.getPhoneNumber(), member.getRole(), member.getSendbirdAccessToken());
    }

}
