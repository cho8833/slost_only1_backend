package com.slost_only1.slost_only1.repository;


import com.slost_only1.slost_only1.model.DolbomNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DolbomNoticeRepositoryCustom {

    Page<DolbomNotice> findByAddress(Pageable pageable, String sido, String sigungu, String bname);
}
