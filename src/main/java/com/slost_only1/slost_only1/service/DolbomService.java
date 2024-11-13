package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomService {
    void postDolbom(DolbomPostReq req);

    Page<DolbomRes> getMyDolbom(DolbomStatus status, Pageable pageable);
}
