package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.DolbomNoticeRes;
import com.slost_only1.slost_only1.data.req.DolbomNoticeListReq;
import com.slost_only1.slost_only1.repository.DolbomNoticeRepository;
import com.slost_only1.slost_only1.service.DolbomNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DolbomNoticeServiceImpl implements DolbomNoticeService {

    private final DolbomNoticeRepository repository;

    @Override
    public Page<DolbomNoticeRes> findByAddress(Pageable pageRequest, DolbomNoticeListReq req) {
        return repository.findByAddress(pageRequest, req.getSido(), req.getSigungu(), req.getBname());
    }

}
