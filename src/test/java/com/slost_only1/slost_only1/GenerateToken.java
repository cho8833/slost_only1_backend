package com.slost_only1.slost_only1;

import com.slost_only1.slost_only1.auth.AuthorizationTokenProvider;
import com.slost_only1.slost_only1.data.AuthorizationTokenData;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenerateToken {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthorizationTokenProvider tokenProvider;

    @Test
    public void generateToken() {
        Member member = memberRepository.findById(1L).orElseThrow();

        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);

        System.out.println(tokenData.getAccessToken());
    }
}
