package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.model.QTeacherDolbom;
import com.slost_only1.slost_only1.model.QTeacherProfile;
import com.slost_only1.slost_only1.model.TeacherProfile;
import lombok.RequiredArgsConstructor;
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
}
