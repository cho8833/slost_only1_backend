package com.slost_only1.slost_only1.service;

import com.slost_only1.slost_only1.data.*;

public interface AuthService {

    AuthorizationTokenData signIn(SignInReq req);

    AuthorizationTokenData signUp(SignUpReq req);

    AuthorizationTokenData reissue(TokenReq req);

    MemberRes me();
}
