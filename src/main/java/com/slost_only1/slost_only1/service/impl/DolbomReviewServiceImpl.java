package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.repository.DolbomReviewRepository;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class DolbomReviewServiceImpl implements DolbomReviewService {

    private final DolbomReviewRepository repository;

    private final AuthUtil authUtil;

    @Override
    public DolbomReview getReviewByDolbomId(Long dolbomId) throws Throwable {
        Long memberId = authUtil.getLoginMemberId();
        return repository.findByDolbomIdAndMemberId(dolbomId, memberId).orElseThrow((Supplier<Throwable>) () -> new CustomException(ResponseCode.DATA_NOT_FOUND));
    }
}
