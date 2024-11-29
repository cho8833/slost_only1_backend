package com.slost_only1.slost_only1.data.req;

public record SendbirdCreateUserReq(
        String user_id,
        String nickname,
        String profile_url,
        Boolean issue_access_token) {
}
