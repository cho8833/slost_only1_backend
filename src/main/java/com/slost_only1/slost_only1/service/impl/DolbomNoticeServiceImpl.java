package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.req.DolbomNoticeReq;
import com.slost_only1.slost_only1.model.DolbomNotice;
import com.slost_only1.slost_only1.repository.DolbomNoticeRepository;
import com.slost_only1.slost_only1.service.DolbomNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DolbomNoticeServiceImpl implements DolbomNoticeService {

    private final DolbomNoticeRepository repository;

    @Override
    public List<DolbomNotice> findByAddress(DolbomNoticeReq req) {
        return repository.findByAddress(req.getSido(), req.getSigungu(), req.getBname());

    }

}
