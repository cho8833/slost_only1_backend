package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.MemberRes;
import com.slost_only1.slost_only1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    @GetMapping("/me")
    public Response<MemberRes> me() {
        return new Response<>(MemberRes.of(memberService.me()));
    }
}
