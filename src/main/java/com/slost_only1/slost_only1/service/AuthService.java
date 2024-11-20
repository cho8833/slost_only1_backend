package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.SignInReq;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.Member;
import jakarta.transaction.Transactional;

public interface AuthService {

    AuthorizationTokenData testSignIn(MemberRole role);

    AuthorizationTokenData signInWithKakao(String phoeNumber, KakaoOAuthToken token, MemberRole role);

    AuthorizationTokenData reissue(TokenReq req);

    MemberRes me();
}
