package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.auth.AuthorizationTokenProvider;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.SendbirdCreateUserReq;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.OAuth;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.*;
import com.slost_only1.slost_only1.service.AuthService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AuthorizationTokenProvider tokenProvider;
    private final AuthUtil authUtil;
    private final KakaoAuthRepository kakaoAuthRepository;
    private final OAuthRepository oAuthRepository;
    private final ChatServiceSendbird chatService;
    private final TeacherProfileRepository teacherProfileRepository;

    @Override
    public AuthorizationTokenData signInWithKakao(MemberRole role, KakaoOAuthToken token) {
        KakaoUser kakaoUser = kakaoAuthRepository.getUserInfo(token.accessToken());

        Optional<OAuth> oAuth = oAuthRepository.findByUserId(kakaoUser.id().toString());

        if (oAuth.isPresent()) {
            Member member = oAuth.get().getMember();
            if (member.getRole() != role) {
                throw new CustomException(ResponseCode.CROSS_USER);
            } else {
                return tokenProvider.generateAuthorizationTokenData(member);
            }
        } else {
            throw new CustomException(ResponseCode.NOT_USER);
        }
    }

    @Override
    public AuthorizationTokenData testSignIn(MemberRole role) {
        Long id;
        if (role == MemberRole.PARENT) {
            id = 3L;
        } else {
            id = 4L;
        }
        Member member = memberRepository.findById(id).orElseThrow();
        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);
        return tokenData;
    }

    @Transactional
    @Override
    public AuthorizationTokenData signUpWithKakao(String phoneNumber, KakaoOAuthToken token, MemberRole role) {
        // 같은 전화번호의 다른 Member 가 있는 경우 DUPLICATE USER Exception
        if (memberRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new CustomException(ResponseCode.DUPLICATE_USER);
        }

        KakaoUser kakaoUser = kakaoAuthRepository.getUserInfo(token.accessToken());
        Member member = signUp(phoneNumber, kakaoUser.id().toString(), AuthProvider.KAKAO, role);

        return tokenProvider.generateAuthorizationTokenData(member);
    }

    @Transactional
    private Member signUp(String phoneNumber, String oAuthUserId, AuthProvider authProvider, MemberRole memberRole) {
        Member member = new Member();
        member.setRole(memberRole);
        member.setPhoneNumber(phoneNumber);
        memberRepository.save(member);

        if (memberRole == MemberRole.TEACHER) {
            TeacherProfile teacherProfile = new TeacherProfile(member);
            teacherProfileRepository.save(teacherProfile);
        }

        OAuth newOAuth = new OAuth(member, oAuthUserId, authProvider);
        oAuthRepository.save(newOAuth);

        // create Sendbird User
        chatService.createUser(member.getId(), member.getRole());

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
