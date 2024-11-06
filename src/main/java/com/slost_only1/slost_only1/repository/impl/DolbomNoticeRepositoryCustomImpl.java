package com.slost_only1.slost_only1.repository.impl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public Page<DolbomNotice> findByAddress(Pageable pageable, String sido, String sigungu, String bname) {

        QDolbomNotice qDolbomNotice = QDolbomNotice.dolbomNotice;
        QDolbomLocation qDolbomLocation = QDolbomLocation.dolbomLocation;
        QMember qMember = QMember.member;
        QKid qKid = QKid.kid;

        List<DolbomNotice> fetch = queryFactory.select(qDolbomNotice)
                .from(qDolbomNotice)
                .innerJoin(qDolbomNotice.dolbomLocation, qDolbomLocation).fetchJoin()
                .innerJoin(qDolbomNotice.member, qMember).fetchJoin()
                .innerJoin(qDolbomNotice.kid, qKid).fetchJoin()
                .where(!StringUtils.isEmpty(sido) ? qDolbomNotice.dolbomLocation.address.sido.eq(sido) : null)
                .where(!StringUtils.isEmpty(sigungu) ? qDolbomNotice.dolbomLocation.address.sigungu.eq(sigungu) : null)
                .where(!StringUtils.isEmpty(bname) ? qDolbomNotice.dolbomLocation.address.bname.eq(bname) : null)
                .fetch();

        JPQLQuery<DolbomNotice> count = queryFactory
                .select(qDolbomNotice)
                .from(qDolbomNotice)
                .where(!StringUtils.isEmpty(sido) ? qDolbomNotice.dolbomLocation.address.sido.eq(sido) : null)
                .where(!StringUtils.isEmpty(sigungu) ? qDolbomNotice.dolbomLocation.address.sigungu.eq(sigungu) : null)
                .where(!StringUtils.isEmpty(bname) ? qDolbomNotice.dolbomLocation.address.bname.eq(bname) : null);

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }


}
