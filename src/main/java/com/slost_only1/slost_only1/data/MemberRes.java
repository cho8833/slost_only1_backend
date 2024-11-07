package com.slost_only1.slost_only1.data;

import com.slost_only1.slost_only1.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MemberRes {

    private Long id;

    private String phoneNumber;

    public static MemberRes of(Member member) {
        return new MemberRes(member.getId(), member.getPhoneNumber());
    }

}
