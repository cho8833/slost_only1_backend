package com.slost_only1.slost_only1.repository;


import com.slost_only1.slost_only1.model.DolbomNotice;

import java.util.List;

public interface DolbomNoticeRepositoryCustom {
    List<DolbomNotice> findByAddress(String sido, String sigungu, String bname);
}
