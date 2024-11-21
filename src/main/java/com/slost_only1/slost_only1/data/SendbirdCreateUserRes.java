package com.slost_only1.slost_only1.data;

import java.util.List;

public record SendbirdCreateUserRes(
        String user_id,
        String nickname,
        String profile_url,
        Boolean require_auth_for_profile_image,
        String access_token,
        List<Object> session_tokens,
        Boolean is_online,
        Integer last_seen_at,
        List<Object> discovery_keys,
        Boolean has_ever_logged_in,
        Boolean is_active,
        Boolean is_created,
        String phone_number
) {
}
