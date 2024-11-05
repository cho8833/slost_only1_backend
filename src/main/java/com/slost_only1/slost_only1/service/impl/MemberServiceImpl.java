package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.api.MemberApi;
import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.MemberReq;
import com.slost_only1.slost_only1.data.MemberRes;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.MemberRepository;
import com.slost_only1.slost_only1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Page<MemberRes> list(MemberApi.SearchRequest request) {
        return memberRepository.findAll(request).map(MemberRes::of);
    }

    @Override
    @Transactional
    public MemberRes update(Long id, MemberReq req) {
        Member member = memberRepository.findById(id).orElseThrow();
        if (req.getRole() == null) {
            throw new CustomException(ResponseCode.WRONG_REQUEST);
        }
//        member.setRole(req.getRole());
        memberRepository.save(member);

        return MemberRes.of(member);
    }
}

