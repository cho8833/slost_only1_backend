package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.model.Member;

public interface MemberService {

    Member me();

    void updateFCMToken(String token);
}
