package com.slost_only1.slost_only1.data;


import com.slost_only1.slost_only1.model.DolbomNotice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DolbomNoticeRes {

    private Long id;

    private DolbomLocationRes dolbomLocation;

    private KidRes kid;

    private MemberRes member;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Long pay;

    public static DolbomNoticeRes of(DolbomNotice dolbomNotice) {
        return new DolbomNoticeRes(
                dolbomNotice.getId(),
                DolbomLocationRes.of(dolbomNotice.getDolbomLocation()),
                KidRes.of(dolbomNotice.getKid()),
                MemberRes.of(dolbomNotice.getMember()),
                dolbomNotice.getStartDateTime(),
                dolbomNotice.getEndDateTime(),
                dolbomNotice.getPay()
        );
    }

}
