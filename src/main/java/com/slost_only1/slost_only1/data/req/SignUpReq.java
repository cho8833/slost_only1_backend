package com.slost_only1.slost_only1.data.req;


import com.slost_only1.slost_only1.data.KakaoOAuthToken;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import lombok.*;

@Builder
public record SignUpReq(
    String phoneNumber,

    String email,

    String oAuthUserId,

    AuthProvider authProvider,

    MemberRole role,

    KakaoOAuthToken kakaoToken

) {


}
