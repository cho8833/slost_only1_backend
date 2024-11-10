package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DolbomRepositoryCustomImpl implements DolbomRepositoryCustom {

    private final JPAQueryFactory queryFactory;


}
