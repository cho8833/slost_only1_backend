package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.AdminSignInReq;
import com.slost_only1.slost_only1.enums.MemberRole;

public interface AuthService {

    AuthorizationTokenData signInWithKakao(MemberRole role, KakaoOAuthToken token);

    AuthorizationTokenData adminSignIn(AdminSignInReq req);

    AuthorizationTokenData testSignIn(MemberRole role);

    AuthorizationTokenData signUpWithKakao(String phoeNumber, KakaoOAuthToken token, MemberRole role);

    AuthorizationTokenData reissue(TokenReq req);
}
