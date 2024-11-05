package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.api.MemberApi;
import com.slost_only1.slost_only1.data.MemberReq;
import com.slost_only1.slost_only1.data.MemberRes;
import org.springframework.data.domain.Page;

public interface MemberService {
    Page<MemberRes> list(MemberApi.SearchRequest request);


    MemberRes update(Long id, MemberReq req);
}
