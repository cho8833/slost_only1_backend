package com.slost_only1.slost_only1.api;

import com.slost_only1.slost_only1.config.response.Response;
import com.slost_only1.slost_only1.data.MemberRes;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.service.impl.ChatServiceSendbird;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendbird")
@RequiredArgsConstructor
public class SendbirdApi {

    private final ChatServiceSendbird chatServiceSendbird;

    private final AuthUtil authUtil;

    @PutMapping("/user")
    public Response<MemberRes> createUser() {
        Long memberId = authUtil.getLoginMemberId();
        MemberRole role = authUtil.getMemberRole();

        return new Response<>(MemberRes.of(chatServiceSendbird.createUser(memberId, role)));
    }
}
