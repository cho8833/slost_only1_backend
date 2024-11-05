package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.auth.AuthorizationTokenProvider;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.MemberRepository;
import com.slost_only1.slost_only1.service.AuthService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AuthorizationTokenProvider tokenProvider;
    private final AuthUtil authUtil;

    @Override
    public AuthorizationTokenData signIn(SignInReq req) {
        Member member = memberRepository.findByUsernameAndPassword(req.getUsername(), req.getPassword()).orElseThrow(() -> {
           throw new CustomException(ResponseCode.NO_USER_FOUND);
        });

        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);

        return tokenData;
    }

    @Override
    public AuthorizationTokenData signUp(SignUpReq req) {
        List<Member> check = memberRepository.findByUsername(req.getUsername());

        if (!check.isEmpty()) {
            throw new CustomException(ResponseCode.DUPLICATE_USERNAME);
        }
        Member member = Member.of(req);
        memberRepository.save(member);

        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);

        return tokenData;
    }

    @Override
    public AuthorizationTokenData reissue(TokenReq req) {
        Member member = memberRepository.findById(tokenProvider.getSubject(req.getAccessToken())).orElseThrow();

        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);

        return tokenData;
    }

    @Override
    public MemberRes me() {
        Member member = memberRepository.findById(authUtil.getLoginMemberId()).orElseThrow(() -> {
            throw new CustomException(ResponseCode.FORBIDDEN);
        });

        return MemberRes.of(member);
    }
}
