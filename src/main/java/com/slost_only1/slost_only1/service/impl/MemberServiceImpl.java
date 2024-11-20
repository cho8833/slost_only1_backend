package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.MemberRepository;
import com.slost_only1.slost_only1.service.MemberService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthUtil authUtil;


    @Override
    public Member me() {
        return memberRepository.findById(authUtil.getLoginMemberId()).orElseThrow();
    }
}
