package com.slost_only1.slost_only1.data.req;

import java.util.List;

public record SendbirdCreateGroupChannelReq(
        List<Long> user_ids,
        List<Long> operator_ids,
        String channel_url,
        Boolean is_distinct) {
}
