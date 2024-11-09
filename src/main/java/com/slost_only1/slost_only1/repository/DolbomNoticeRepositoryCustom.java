package com.slost_only1.slost_only1.repository;


import com.slost_only1.slost_only1.data.DolbomNoticeRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomNoticeRepositoryCustom {

    Page<DolbomNoticeRes> findByAddress(Pageable pageable, String sido, String sigungu, String bname);
}
