package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class KidRepositoryCustomImpl implements KidRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static QKid qKid = QKid.kid;

    @Override
    public Map<Long, List<Kid>> findByDolbomIdsIn(List<Long> dolbomIds) {
        QKidDolbom qKidDolbom = QKidDolbom.kidDolbom;
        QDolbom qDolbom       = QDolbom.dolbom;

        return queryFactory
                // 1) KidDolbom 엔티티 전체를 select
                .select(qKidDolbom)
                .from(qDolbom)
                .innerJoin(qKidDolbom)
                .on(qKidDolbom.dolbom.id.eq(qDolbom.id)).fetchJoin()
                .innerJoin(qKidDolbom.kid, qKid).fetchJoin()
                .where(qDolbom.id.in(dolbomIds))
                .fetch()  // List<KidDolbom> 반환
                .stream()
                // 2) Dolbom ID 기준으로 Kid 리스트 그룹핑
                .collect(Collectors.groupingBy(
                        kidDolbom -> kidDolbom.getDolbom().getId(),       // key = dolbomId
                        Collectors.mapping(KidDolbom::getKid, Collectors.toList())  // value = Kid 리스트
                ));
    }

}
