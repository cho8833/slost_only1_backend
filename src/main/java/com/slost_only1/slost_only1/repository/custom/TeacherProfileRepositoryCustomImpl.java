package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import com.slost_only1.slost_only1.model.QAvailableArea;
import com.slost_only1.slost_only1.model.QDolbomAppliedTeacher;
import com.slost_only1.slost_only1.model.QTeacherProfile;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.util.BooleanExpressionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherProfileRepositoryCustomImpl implements TeacherProfileRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QDolbomAppliedTeacher qTeacherDolbom = QDolbomAppliedTeacher.dolbomAppliedTeacher;
    QTeacherProfile qTeacherProfile = QTeacherProfile.teacherProfile;
    QAvailableArea qAvailableArea = QAvailableArea.availableArea;

    @Override
    public List<TeacherProfile> findByDolbomId(Long id) {
        return queryFactory.select(qTeacherProfile)
                .from(qTeacherDolbom)
                .innerJoin(qTeacherDolbom.teacherProfile, qTeacherProfile)
                .where(qTeacherDolbom.dolbom.id.eq(id))
                .fetch();
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
