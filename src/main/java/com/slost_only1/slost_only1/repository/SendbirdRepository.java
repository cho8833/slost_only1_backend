package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.data.SendbirdCreateUserRes;
import com.slost_only1.slost_only1.model.Member;

public interface SendbirdRepository {
    SendbirdCreateUserRes createUser(Member member);
}
