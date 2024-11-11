package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.core.ResultTransformer;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
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
    QKid qKid = QKid.kid;
    QKidDolbom qKidDolbom = QKidDolbom.kidDolbom;
    QDolbomDow qDolbomDow = QDolbomDow.dolbomDow;
    QTeacherProfile qTeacherProfile = QTeacherProfile.teacherProfile;
    QDolbomTimeSlot qDolbomTimeSlot = QDolbomTimeSlot.dolbomTimeSlot;
    QDolbomReview qDolbomReview = QDolbomReview.dolbomReview;


    @Override
    public Page<DolbomRes> findByMemberIdAndAddressAndStatus(Long memberId, AddressListReq addressListReq, DolbomStatus status, Pageable pageable) {
        List<DolbomRes> fetch = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbom.dolbomLocation, qDolbomLocation)
                .innerJoin(qDolbomTimeSlot).on(qDolbomTimeSlot.dolbom.id.eq(qDolbom.id))
                .innerJoin(qKidDolbom).on(qKidDolbom.dolbom.id.eq(qDolbom.id))
                .innerJoin(qKidDolbom.kid, qKid)
                .leftJoin(qDolbom.teacherProfile, qTeacherProfile)
                .leftJoin(qDolbomReview).on(qDolbomReview.dolbom.id.eq(qDolbom.id))
                .leftJoin(qDolbomDow).on(qDolbomDow.dolbom.id.eq(qDolbom.id))
                .where(
                        BooleanExpressionUtil.eq(qDolbom.member.id, memberId),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sido, addressListReq.getSido()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sigungu, addressListReq.getSigungu()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.bname, addressListReq.getBname()),
                        BooleanExpressionUtil.eq(qDolbom.status, status)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(dolbomResProjection());

        JPAQuery<Dolbom> count = queryFactory.selectFrom(qDolbom)
                .innerJoin(qDolbom.dolbomLocation, qDolbomLocation)
                .where(
                        BooleanExpressionUtil.eq(qDolbom.member.id, memberId),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sido, addressListReq.getSido()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.sigungu, addressListReq.getSigungu()),
                        BooleanExpressionUtil.eq(qDolbom.dolbomLocation.address.bname, addressListReq.getBname()),
                        BooleanExpressionUtil.eq(qDolbom.status, status)
                );
        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }

    ResultTransformer<List<DolbomRes>> dolbomResProjection() {
        return GroupBy.groupBy(qDolbom.id).list(
                new QDolbomRes(qDolbom.id,
                        GroupBy.list(new QKidRes(qKid.id, qKid.name, qKid.birthday, qKid.gender, qKid.tendency, qKid.remark)),
                        GroupBy.list(new QDolbomTimeSlotRes(qDolbomTimeSlot.id, qDolbomTimeSlot.startDateTime, qDolbomTimeSlot.endDateTime, qDolbomTimeSlot.status)),
                        new QTeacherProfileRes(qTeacherProfile.id, qTeacherProfile.name, qTeacherProfile.gender, qTeacherProfile.profileImageUrl, qTeacherProfile.age, qTeacherProfile.profileName).skipNulls(),
                        new QDolbomLocationRes(qDolbomLocation.id, qDolbomLocation.address, qDolbomLocation.name),
                        qDolbom.startTime,
                        qDolbom.endTime,
                        qDolbom.status,
                        qDolbom.category,
                        qDolbom.weeklyRepeat,
                        qDolbom.setSeveralTime,
                        qDolbom.repeatStartDate,
                        qDolbom.repeatEndDate
                )
        );
    }


}
