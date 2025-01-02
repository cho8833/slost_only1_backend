package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import com.slost_only1.slost_only1.model.*;
import com.slost_only1.slost_only1.util.BooleanExpressionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TeacherProfileRepositoryCustomImpl implements TeacherProfileRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QDolbomAppliedTeacher qTeacherDolbom = QDolbomAppliedTeacher.dolbomAppliedTeacher;
    QTeacherProfile qTeacherProfile = QTeacherProfile.teacherProfile;
    QAvailableArea qAvailableArea = QAvailableArea.availableArea;

    @Override
    public List<TeacherProfile> findByAppliedDolbomId(Long id) {
        return queryFactory.select(qTeacherProfile)
                .from(qTeacherDolbom)
                .innerJoin(qTeacherDolbom.teacherProfile, qTeacherProfile)
                .where(qTeacherDolbom.dolbom.id.eq(id))
                .fetch();
    }

    @Override
    public Map<Long, List<TeacherProfile>> findByAppliedDolbomIdsIn(List<Long> ids) {
        return queryFactory
                .select(qTeacherDolbom)
                .from(qTeacherDolbom)
                .innerJoin(qTeacherDolbom.teacherProfile, qTeacherProfile).fetchJoin()
                .where(qTeacherDolbom.dolbom.id.in(ids))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(
                        teacherDolbom -> teacherDolbom.getDolbom().getId(), // key = dolbomId
                        Collectors.mapping(DolbomAppliedTeacher::getTeacherProfile, Collectors.toList())
                ));
    }

    @Override
    public TeacherProfile findByDolbomId(Long id) {
        QDolbom qDolbom = QDolbom.dolbom;

        return queryFactory.selectFrom(qTeacherProfile)
                .innerJoin(qDolbom).on(qDolbom.teacherProfile.id.eq(qTeacherProfile.id))
                .where(qDolbom.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<TeacherProfile> findBySigunguAndSido(String sigungu, String sido, Pageable pageable) {
        List<TeacherProfile> fetch = queryFactory.selectFrom(qTeacherProfile)
                .where(BooleanExpressionUtil.eq(qAvailableArea.sigungu, sigungu),
                        BooleanExpressionUtil.eq(qTeacherProfile.status, TeacherProfileStatus.APPROVED))
                .innerJoin(qAvailableArea).on(qAvailableArea.teacherProfile.id.eq(qTeacherProfile.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<TeacherProfile> count = queryFactory.selectFrom(qTeacherProfile)
                .innerJoin(qAvailableArea).on(qAvailableArea.teacherProfile.id.eq(qTeacherProfile.id))
                .where(BooleanExpressionUtil.eq(qAvailableArea.sigungu, sigungu), BooleanExpressionUtil.eq(qTeacherProfile.status, TeacherProfileStatus.APPROVED));

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }
}
