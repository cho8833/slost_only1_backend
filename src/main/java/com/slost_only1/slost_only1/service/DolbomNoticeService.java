package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.DolbomNoticeListReq;
import com.slost_only1.slost_only1.model.DolbomNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface DolbomNoticeService {
    Page<DolbomNotice> findByAddress(Pageable pageRequest, DolbomNoticeListReq req);
}
