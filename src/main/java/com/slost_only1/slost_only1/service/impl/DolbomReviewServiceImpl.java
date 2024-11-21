package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.req.DolbomReviewCreateReq;
import com.slost_only1.slost_only1.model.Dolbom;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.DolbomReviewRepository;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DolbomReviewServiceImpl implements DolbomReviewService {

    private final DolbomReviewRepository repository;

    private final AuthUtil authUtil;

    private final EntityManager entityManager;

    @Override
    public Optional<DolbomReview> getReviewByDolbomId(Long dolbomId) throws Throwable {
        Long memberId = authUtil.getLoginMemberId();
        return repository.findByDolbomIdAndMemberId(dolbomId, memberId);
    }

    @Override
    public DolbomReview create(DolbomReviewCreateReq req) {
        Member member = entityManager.getReference(Member.class, authUtil.getLoginMemberId());
        Dolbom dolbom = entityManager.getReference(Dolbom.class, req.dolbomId());

        DolbomReview dolbomReview = new DolbomReview(dolbom, req.content(), req.star(), member);
        repository.save(dolbomReview);

        return dolbomReview;
    }
}