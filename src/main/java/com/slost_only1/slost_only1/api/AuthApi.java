package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.SignInReq;
import com.slost_only1.slost_only1.data.req.SignUpReq;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/sign-in/test")
    public Response<AuthorizationTokenData> testSignIn(@RequestParam MemberRole role) {
//        return new Response<>(authService.signIn(req));
        return new Response<>(authService.testSignIn(role));
    }

    @PostMapping("/sign-in")
    public Response<AuthorizationTokenData> signInWithKakao(@RequestBody SignInReq req) throws BadRequestException {
        if (req.getAuthProvider() == AuthProvider.KAKAO) {
            return new Response<>(authService.signInWithKakao(req.getPhoneNumber(), req.getKakaoToken(), req.getRole()));
        }
        throw new BadRequestException();
    }

    @PostMapping("/token")
    public Response<AuthorizationTokenData> token(@RequestBody TokenReq req) {
        return new Response<>(authService.reissue(req));
    }
}
