package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.auth.AuthorizationTokenProvider;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.*;
import com.slost_only1.slost_only1.data.req.AdminSignInReq;
import com.slost_only1.slost_only1.data.req.SignUpReq;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.enums.MemberRole;
import com.slost_only1.slost_only1.model.JwtPublicKey;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.OAuth;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.*;
import com.slost_only1.slost_only1.service.AuthService;
import com.slost_only1.slost_only1.util.AuthUtil;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final AuthorizationTokenProvider tokenProvider;
    private final KakaoAuthRepository kakaoAuthRepository;
    private final OAuthRepository oAuthRepository;
    private final ChatServiceSendbird chatService;
    private final TeacherProfileRepository teacherProfileRepository;
    private final AuthUtil authUtil;

    @Override
    public AuthorizationTokenData signInWithKakao(MemberRole role, KakaoOAuthToken token) {
        KakaoUser kakaoUser = kakaoAuthRepository.getUserInfo(token.accessToken());

        Optional<OAuth> oAuth = oAuthRepository.findByUserIdAndAuthProvider(
                kakaoUser.id().toString(), AuthProvider.KAKAO);

        if (oAuth.isPresent()) {
            return onSignInSuccess(role, oAuth.get());
        }
        // 회원이 존재하지 않을 시, 바로 회원가입
        else {
            SignUpReq req = SignUpReq.builder()
                    .oAuthUserId(kakaoUser.id().toString())
                    .authProvider(AuthProvider.KAKAO)
                    .role(role)
                    .build();
            Member member = signUp(req);

            return tokenProvider.generateAuthorizationTokenData(member);
        }
    }

    @Override
    public AuthorizationTokenData adminSignIn(AdminSignInReq req) {
        Member member = memberRepository.findByUsernameAndPassword(req.id(), req.password())
                .orElseThrow(() -> new CustomException(ResponseCode.NO_USER_FOUND));

        if (member.getRole() != MemberRole.ADMIN) {
            throw new CustomException(ResponseCode.FORBIDDEN);
        }

        return tokenProvider.generateAuthorizationTokenData(member);
    }

    @Override
    public AuthorizationTokenData testSignIn(MemberRole role) {
        Long id;
        if (role == MemberRole.PARENT) {
            id = 2L;
        } else {
            id = 1L;
        }
        Member member = memberRepository.findById(id).orElseThrow();
        AuthorizationTokenData tokenData = tokenProvider.generateAuthorizationTokenData(member);
        return tokenData;
    }

    @Transactional
    private Member signUp(SignUpReq req) {
        Member member = new Member();
        member.setRole(req.role());
        member.setEmail(req.email());
        member.setPhoneNumber(req.phoneNumber());
        memberRepository.save(member);

        if (req.role() == MemberRole.TEACHER) {
            TeacherProfile teacherProfile = new TeacherProfile(member);
            teacherProfileRepository.save(teacherProfile);
        }

        OAuth newOAuth = new OAuth(member, req.oAuthUserId(), req.authProvider());
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
    public AuthorizationTokenData signInWithApple(MemberRole role, AppleAuthorizationCredential credential) {
        Claims claims = tokenProvider.parseClaims(AuthProvider.APPLE, credential.identityToken());
        String sub = (String) claims.get("sub");

        Optional<OAuth> oAuth = oAuthRepository.findByUserIdAndAuthProvider(sub, AuthProvider.APPLE);

        if (oAuth.isPresent()) {
            return onSignInSuccess(role, oAuth.get());
        }
        // 회원이 존재하지 않을 시 바로 회원가입
        else {
            SignUpReq req = SignUpReq.builder()
                    .email((String) claims.get("email"))
                    .oAuthUserId(sub)
                    .authProvider(AuthProvider.APPLE)
                    .role(role)
                    .build();
            Member member = signUp(req);

            return tokenProvider.generateAuthorizationTokenData(member);
        }
    }

    @Override
    @Transactional
    public void withdrawal() {
        Long memberId = authUtil.getLoginMemberId();
        oAuthRepository.deleteByMember_Id(memberId);
        memberRepository.deleteById(memberId);
    }

    private AuthorizationTokenData onSignInSuccess(MemberRole role, OAuth oAuth) {
        Member member = oAuth.getMember();
        if (member.getRole() != role) {
            throw new CustomException(ResponseCode.CROSS_USER);
        } else {
            return tokenProvider.generateAuthorizationTokenData(member);
        }
    }
}
