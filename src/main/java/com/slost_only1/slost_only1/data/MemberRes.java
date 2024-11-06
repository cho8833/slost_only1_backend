package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRes {

    private Long id;

    private String phoneNumber;


    public MemberRes(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public static MemberRes of(Member member) {
        return new MemberRes(member.getId(), member.getPhoneNumber());
    }

}
