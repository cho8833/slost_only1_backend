package com.slost_only1.slost_only1.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.repository.DolbomNoticeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DolbomNoticeRepositoryCustomImpl implements DolbomNoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<DolbomNotice> findByAddress(String sido, String sigungu, String bname) {

        QDolbomNotice qDolbomNotice = QDolbomNotice.dolbomNotice;
        QDolbomLocation qDolbomLocation = QDolbomLocation.dolbomLocation;
        QMember qMember = QMember.member;
        QKid qKid = QKid.kid;

        return queryFactory.select(qDolbomNotice)
                .from(qDolbomNotice)
                .innerJoin(qDolbomNotice.dolbomLocation, qDolbomLocation).fetchJoin()
                .innerJoin(qDolbomNotice.member, qMember).fetchJoin()
                .innerJoin(qDolbomNotice.kid, qKid).fetchJoin()
                .where( sido != null ? qDolbomNotice.dolbomLocation.address.sido.eq(sido) : null)
                .where( sigungu != null ? qDolbomNotice.dolbomLocation.address.sigungu.eq(sigungu) : null)
                .where( bname != null ? qDolbomNotice.dolbomLocation.address.bname.eq(bname) : null)
                .fetch();
    }


}
