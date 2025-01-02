package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.core.ResultTransformer;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.util.BooleanExpressionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DolbomRepositoryCustomImpl implements DolbomRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QDolbom qDolbom = QDolbom.dolbom;
    QDolbomLocation qDolbomLocation = QDolbomLocation.dolbomLocation;
    QTeacherProfile qTeacherProfile = QTeacherProfile.teacherProfile;

    @Override
    public Page<Dolbom> findByMemberIdAndAddressAndStatus(Long memberId, AddressListReq addressListReq, DolbomStatus status, Pageable pageable) {
        List<Dolbom> fetch = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbom.dolbomLocation, qDolbomLocation).fetchJoin()
                .where(
                        BooleanExpressionUtil.eq(qDolbom.member.id, memberId),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sido, addressListReq.getSido()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sigungu, addressListReq.getSigungu()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.bname, addressListReq.getBname()),
                        BooleanExpressionUtil.eq(qDolbom.status, status)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(qDolbom.count())
                .from(qDolbom)
                .innerJoin(qDolbom.dolbomLocation, qDolbomLocation)
                .where(
                        BooleanExpressionUtil.eq(qDolbom.member.id, memberId),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sido, addressListReq.getSido()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sigungu, addressListReq.getSigungu()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.bname, addressListReq.getBname()),
                        BooleanExpressionUtil.eq(qDolbom.status, status)
                );
        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }

    @Override
    public Page<Dolbom> findByAppliedTeacherId(Long teacherId, Pageable pageable) {
        QDolbomAppliedTeacher qDolbomAppliedTeacher = QDolbomAppliedTeacher.dolbomAppliedTeacher;
        List<Dolbom> fetch = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbom.dolbomLocation, qDolbomLocation).fetchJoin()
                .innerJoin(qDolbomAppliedTeacher).on(qDolbomAppliedTeacher.dolbom.id.eq(qDolbom.id))
                .innerJoin(qDolbomAppliedTeacher.teacherProfile, qTeacherProfile)
                .where(
                        BooleanExpressionUtil.eq(qDolbom.status, DolbomStatus.MATCHING),
                        BooleanExpressionUtil.eq(qTeacherProfile.member.id, teacherId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Dolbom> count = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbomAppliedTeacher).on(qDolbomAppliedTeacher.dolbom.id.eq(qDolbom.id))
                .innerJoin(qDolbomAppliedTeacher.teacherProfile, qTeacherProfile)
                .where(
                        BooleanExpressionUtil.eq(qTeacherProfile.member.id, teacherId),
                        BooleanExpressionUtil.eq(qDolbom.status, DolbomStatus.MATCHING)
                );
        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }

    @Override
    public Page<Dolbom> findByTeacherIdAndStatus(Long teacherId, DolbomStatus status, Pageable pageable) {
        List<Dolbom> fetch = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbom.dolbomLocation, qDolbomLocation).fetchJoin()
                .innerJoin(qDolbom.teacherProfile, qTeacherProfile)
                .where(
                        BooleanExpressionUtil.eq(qDolbom.status, status),
                        BooleanExpressionUtil.eq(qTeacherProfile.member.id, teacherId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Dolbom> count = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbom.teacherProfile, qTeacherProfile)
                .where(
                        BooleanExpressionUtil.eq(qTeacherProfile.member.id, teacherId),
                        BooleanExpressionUtil.eq(qDolbom.status, status)
                );
        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }
}
