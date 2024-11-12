package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import com.slost_only1.slost_only1.model.QTeacherDolbom;
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
    QTeacherDolbom qTeacherDolbom = QTeacherDolbom.teacherDolbom;
    QTeacherProfile qTeacherProfile = QTeacherProfile.teacherProfile;

    @Override
    public List<TeacherProfile> findByDolbomId(Long id) {
        return queryFactory.select(qTeacherProfile)
                .from(qTeacherDolbom)
                .innerJoin(qTeacherDolbom.teacherProfile, qTeacherProfile)
                .where(qTeacherDolbom.dolbom.id.eq(id))
                .fetch();
    }

    @Override
    public Page<TeacherProfile> findByBname(String bname, Pageable pageable) {
        List<TeacherProfile> fetch =  queryFactory.selectFrom(qTeacherProfile)
                .where(BooleanExpressionUtil.eq(qTeacherProfile.address.bname, bname),
                        BooleanExpressionUtil.eq(qTeacherProfile.status, TeacherProfileStatus.APPROVED))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<TeacherProfile> count = queryFactory.selectFrom(qTeacherProfile)
                .where(BooleanExpressionUtil.eq(qTeacherProfile.address.bname, bname));

        return PageableExecutionUtils.getPage(fetch,pageable, count::fetchCount);

    }
}
