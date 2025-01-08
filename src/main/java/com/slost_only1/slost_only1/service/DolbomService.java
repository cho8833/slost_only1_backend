package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.data.req.DolbomPostReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomService {
    void postDolbom(DolbomPostReq req);

    Page<DolbomRes> getParentDolbom(DolbomStatus status, Pageable pageable);

    Page<DolbomRes> getMatchingDolbom(AddressListReq addressReq, Pageable pageable);

    Page<DolbomRes> getTeacherDolbom(DolbomStatus status, Pageable pageReq);

    Page<DolbomRes> getTeacherAppliedDolbom(Pageable pageReq);

    void apply(Long dolbomId);

    void match(Long dolbomId, Long teacherProfileId);

    void finishDolbom(Long dolbomId);
}
