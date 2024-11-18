package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.repository.DolbomReviewRepository;
import com.slost_only1.slost_only1.service.DolbomReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DolbomReviewServiceImpl implements DolbomReviewService {

    private final DolbomReviewRepository repository;

}
