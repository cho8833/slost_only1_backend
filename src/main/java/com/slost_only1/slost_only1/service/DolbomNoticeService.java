package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.DolbomNoticeRes;
import com.slost_only1.slost_only1.data.req.DolbomNoticeListReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomNoticeService {
    Page<DolbomNoticeRes> findByAddress(Pageable pageRequest, DolbomNoticeListReq req);
}
