package com.slost_only1.slost_only1.data.req;

import java.util.List;

public record SendbirdCreateGroupChannelReq(
        List<String> user_ids,
        List<String> operator_ids,
        String channel_url,
        Boolean is_distinct) {
}
