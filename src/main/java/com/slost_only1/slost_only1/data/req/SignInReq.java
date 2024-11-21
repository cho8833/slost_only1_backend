package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.data.KakaoOAuthToken;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record SignInReq(AuthProvider authProvider, MemberRole role, KakaoOAuthToken kakaoToken) {
}
