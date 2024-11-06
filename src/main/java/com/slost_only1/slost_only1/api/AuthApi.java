package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.SignInReq;
import com.slost_only1.slost_only1.data.req.SignUpReq;
import com.slost_only1.slost_only1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/signIn")
    public Response<AuthorizationTokenData> signIn(@RequestBody SignInReq req) {
        return new Response<>(authService.signIn(req));
    }

    @PostMapping("/signUp")
    public Response<AuthorizationTokenData> signUp(@RequestBody SignUpReq req) {
        return new Response<>(authService.signUp(req));
    }

    @PostMapping("/token")
    public Response<AuthorizationTokenData> token(@RequestBody TokenReq req) {
        return new Response<>(authService.reissue(req));
    }

    @GetMapping("/me")
    public Response<MemberRes> me() {
        return new Response<>(authService.me());
    }
}
