package com.slost_only1.slost_only1.data;


import com.querydsl.core.annotations.QueryProjection;
import com.slost_only1.slost_only1.enums.DolbomCategory;
import com.slost_only1.slost_only1.model.DolbomNotice;
import com.slost_only1.slost_only1.model.Kid;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DolbomNoticeRes {

    private Long id;

    private DolbomLocationRes dolbomLocation;

    private List<KidRes> kid;

    private MemberRes member;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Long pay;

    private DolbomCategory category;

    @QueryProjection
    public DolbomNoticeRes(Long id, DolbomLocationRes dolbomLocation, List<KidRes> kid, MemberRes member, LocalDateTime startDateTime, LocalDateTime endDateTime, Long pay, DolbomCategory category) {
        this.id = id;
        this.dolbomLocation = dolbomLocation;
        this.kid = kid;
        this.member = member;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.pay = pay;
        this.category = category;
    }

    public static DolbomNoticeRes of(DolbomNotice dolbomNotice, List<Kid> kids) {
        return new DolbomNoticeRes(
                dolbomNotice.getId(),
                DolbomLocationRes.of(dolbomNotice.getDolbomLocation()),
                kids.stream().map(KidRes::of).toList(),
                MemberRes.of(dolbomNotice.getMember()),
                dolbomNotice.getStartDateTime(),
                dolbomNotice.getEndDateTime(),
                dolbomNotice.getPay(),
                dolbomNotice.getCategory()
        );
    }

}
