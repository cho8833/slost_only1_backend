package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.data.DolbomRes;
import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomRepositoryCustom {

    Page<DolbomRes> findByMemberIdAndAddressAndStatus(Long memberId, AddressListReq addressListReq, DolbomStatus status, Pageable pageable);

    Page<DolbomRes> findByAppliedTeacherId(Long teacherId, Pageable pageable);

    Page<DolbomRes> findByTeacherIdAndStatus(Long teacherId, DolbomStatus status, Pageable pageable);
}
