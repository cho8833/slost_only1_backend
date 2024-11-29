package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.data.SendbirdCreateGroupChannelRes;
import com.slost_only1.slost_only1.data.SendbirdCreateUserRes;
import com.slost_only1.slost_only1.data.req.SendbirdCreateGroupChannelReq;
import com.slost_only1.slost_only1.data.req.SendbirdCreateUserReq;

public interface SendbirdRepository {
    SendbirdCreateUserRes createUser(SendbirdCreateUserReq req);

    SendbirdCreateGroupChannelRes createGroupChannel(SendbirdCreateGroupChannelReq req);
}
