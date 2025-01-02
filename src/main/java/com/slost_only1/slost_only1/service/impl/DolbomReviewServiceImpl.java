package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.req.DolbomReviewCreateReq;
import com.slost_only1.slost_only1.data.req.ReviewReportReq;
import com.slost_only1.slost_only1.model.Dolbom;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.DolbomReviewRepository;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return repository.findByDolbomId(dolbomId);
    }

    @Override
    public DolbomReview create(DolbomReviewCreateReq req) {
        Member member = entityManager.getReference(Member.class, authUtil.getLoginMemberId());
        Dolbom dolbom = entityManager.getReference(Dolbom.class, req.dolbomId());

        DolbomReview dolbomReview = new DolbomReview(dolbom,
                req.content(),
                req.star(),
                member,
                dolbom.getTeacherProfile(),
                null, null);
        repository.save(dolbomReview);

        return dolbomReview;
    }

    @Override
    public void report(ReviewReportReq req) {
        DolbomReview review = repository.findById(req.reviewId()).orElseThrow();

        review.setReportContent(req.content());
        review.setReportReason(req.reason());

        repository.save(review);
    }

    @Override
    public Page<DolbomReview> getReported(Pageable pageable) {
        return repository.findByReportReasonNotNull(pageable);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
