package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.auth.AuthorizationTokenProvider;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.SignInReq;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.OAuth;
import com.slost_only1.slost_only1.repository.KakaoAuthRepository;
import com.slost_only1.slost_only1.repository.MemberRepository;
import com.slost_only1.slost_only1.repository.OAuthRepository;
import com.slost_only1.slost_only1.service.AuthService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    private final AuthorizationTokenProvider tokenProvider;
    private final AuthUtil authUtil;
    private final KakaoAuthRepository kakaoAuthRepository;
    private final OAuthRepository oAuthRepository;

    @Override
    public AuthorizationTokenData testSignIn(MemberRole role) {
        Long id;
        if (role == MemberRole.PARENT) {
            id = 3L;
        } else {
            id = 1L;
        }
        Member member = memberRepository.findById(id).orElseThrow();
        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);
        return tokenData;
    }

    @Transactional
    @Override
    public AuthorizationTokenData signInWithKakao(String phoneNumber, KakaoOAuthToken token, MemberRole role) {

        KakaoUser kakaoUser = kakaoAuthRepository.getUserInfo(token.accessToken());

        Optional<OAuth> oAuth = oAuthRepository.findByUserId(kakaoUser.id().toString());

        Member member;
        if (oAuth.isPresent()) {
            member = memberRepository.findById(oAuth.get().getMember().getId()).orElseThrow();
            return tokenProvider.generateAuthorizationTokenData(member);
        } else {
            // 같은 전화번호의 다른 Member 가 있는 경우 DUPLICATE USER Exception
            if (memberRepository.findByPhoneNumber(phoneNumber).isPresent()) {
                throw new CustomException(ResponseCode.DUPLICATE_USER);
            }
            member = signUp(phoneNumber, kakaoUser.id().toString(), AuthProvider.KAKAO, role);
        }
        return tokenProvider.generateAuthorizationTokenData(member);
    }

    @Transactional
    private Member signUp(String phoneNumber, String oAuthUserId, AuthProvider authProvider, MemberRole memberRole) {
        Member member = new Member();
        member.setRole(memberRole);
        member.setPhoneNumber(phoneNumber);
        memberRepository.save(member);
        OAuth newOAuth = new OAuth(member, oAuthUserId, authProvider);
        oAuthRepository.save(newOAuth);
        return member;
    }

    @Override
    public AuthorizationTokenData reissue(TokenReq req) {
        Member member = memberRepository.findById(tokenProvider.getSubject(req.getAccessToken())).orElseThrow();

        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);

        return tokenData;
    }

    @Override
    public MemberRes me() {
        Member member = memberRepository.findById(authUtil.getLoginMemberId()).orElseThrow(() -> new CustomException(ResponseCode.FORBIDDEN));

        return MemberRes.of(member);
    }
}
