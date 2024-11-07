package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.req.KidCreateReq;
import com.slost_only1.slost_only1.data.req.KidUpdateReq;
import com.slost_only1.slost_only1.model.Kid;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.KidRepository;
import com.slost_only1.slost_only1.service.KidService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class KidServiceImpl implements KidService {

    private final KidRepository repository;

    private final AuthUtil authUtil;

    private final EntityManager entityManager;

    @Override
    public List<Kid> getMyKids() {
        return repository.findByMemberId(authUtil.getLoginMemberId());
    }

    @Override
    public Kid createKid(KidCreateReq req) {
        Member member = entityManager.find(Member.class, authUtil.getLoginMemberId());
        Kid newKid = Kid.from(req, member);

        repository.save(newKid);

        return newKid;
    }

    @Override
    public void deleteKid(Long id) {
        Kid kid = repository.findById(id).orElseThrow();

        if (!Objects.equals(kid.getMember().getId(), authUtil.getLoginMemberId())) {
            throw new CustomException(ResponseCode.FORBIDDEN);
        }

        repository.delete(kid);

    }

    @Override
    public Kid updateKid(KidUpdateReq req) {
        Kid kid = repository.findById(req.getId()).orElseThrow();

        kid.copy(req);

        repository.save(kid);

        return kid;
    }
}
