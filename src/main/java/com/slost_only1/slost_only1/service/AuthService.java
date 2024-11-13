package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.SignInReq;
import com.slost_only1.slost_only1.data.req.SignUpReq;
import com.slost_only1.slost_only1.enums.MemberRole;

public interface AuthService {

    AuthorizationTokenData signIn(SignInReq req);

    AuthorizationTokenData testSignIn(MemberRole role);

    AuthorizationTokenData signUp(SignUpReq req);

    AuthorizationTokenData reissue(TokenReq req);

    MemberRes me();
}
