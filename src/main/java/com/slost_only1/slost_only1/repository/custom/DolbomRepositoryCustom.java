package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.data.req.AddressListReq;
import com.slost_only1.slost_only1.enums.DolbomStatus;
import com.slost_only1.slost_only1.model.Dolbom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomRepositoryCustom {

    Page<Dolbom> findByMemberIdAndAddressAndStatus(Long memberId, AddressListReq addressListReq, DolbomStatus status, Pageable pageable);

    Page<Dolbom> findByAppliedTeacherId(Long teacherId, Pageable pageable);

    Page<Dolbom> findByTeacherIdAndStatus(Long teacherId, DolbomStatus status, Pageable pageable);
}
