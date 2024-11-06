package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.req.DolbomNoticeReq;
import com.slost_only1.slost_only1.model.DolbomNotice;

import java.util.List;

public interface DolbomNoticeService {
    List<DolbomNotice> findByAddress(DolbomNoticeReq req);
}
