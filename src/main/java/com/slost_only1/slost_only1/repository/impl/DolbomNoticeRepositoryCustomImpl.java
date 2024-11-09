package com.slost_only1.slost_only1.repository.impl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.repository.DolbomNoticeRepositoryCustom;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DolbomNoticeRepositoryCustomImpl implements DolbomNoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<DolbomNoticeRes> findByAddress(Pageable pageable, String sido, String sigungu, String bname) {

        QDolbomNotice qDolbomNotice = QDolbomNotice.dolbomNotice;
        QDolbomLocation qDolbomLocation = QDolbomLocation.dolbomLocation;
        QMember qMember = QMember.member;
        QKidDolbomNotice qKidDolbomNotice = QKidDolbomNotice.kidDolbomNotice;
        QKid qKid = QKid.kid;

        List<DolbomNoticeRes> fetch = queryFactory.select(qDolbomNotice)
                .from(qDolbomNotice)
                .innerJoin(qDolbomNotice.dolbomLocation, qDolbomLocation)
                .innerJoin(qDolbomNotice.member, qMember)
                .innerJoin(qKidDolbomNotice).on(qKidDolbomNotice.dolbomNotice.id.eq(qDolbomNotice.id))
                .innerJoin(qKidDolbomNotice.kid, qKid)
                .where(!StringUtils.isEmpty(sido) ? qDolbomNotice.dolbomLocation.address.sido.eq(sido) : null)
                .where(!StringUtils.isEmpty(sigungu) ? qDolbomNotice.dolbomLocation.address.sigungu.eq(sigungu) : null)
                .where(!StringUtils.isEmpty(bname) ? qDolbomNotice.dolbomLocation.address.bname.eq(bname) : null)
                .transform(
                        GroupBy.groupBy(qDolbomNotice.id).list(
                                new QDolbomNoticeRes(
                                        qDolbomNotice.id, new QDolbomLocationRes(qDolbomLocation.id, qDolbomLocation.address, qDolbomLocation.name),
                                        GroupBy.list(new QKidRes(qKid.id, qKid.name, qKid.birthday, qKid.gender, qKid.tendency, qKid.remark)),
                                        new QMemberRes(qMember.id, qMember.phoneNumber),
                                        qDolbomNotice.startDateTime,
                                        qDolbomNotice.endDateTime,
                                        qDolbomNotice.pay
                                )
                        )
                );

        JPQLQuery<DolbomNotice> count = queryFactory
                .select(qDolbomNotice)
                .from(qDolbomNotice)
                .where(!StringUtils.isEmpty(sido) ? qDolbomNotice.dolbomLocation.address.sido.eq(sido) : null)
                .where(!StringUtils.isEmpty(sigungu) ? qDolbomNotice.dolbomLocation.address.sigungu.eq(sigungu) : null)
                .where(!StringUtils.isEmpty(bname) ? qDolbomNotice.dolbomLocation.address.bname.eq(bname) : null);
        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }


}
