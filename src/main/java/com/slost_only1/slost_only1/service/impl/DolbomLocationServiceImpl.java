package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.config.exception.CustomException;
import com.slost_only1.slost_only1.config.response.ResponseCode;
import com.slost_only1.slost_only1.data.req.DolbomLocationCreateReq;
import com.slost_only1.slost_only1.model.DolbomLocation;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.DolbomLocationRepository;
import com.slost_only1.slost_only1.service.DolbomLocationService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DolbomLocationServiceImpl implements DolbomLocationService {

    private final DolbomLocationRepository repository;

    private final AuthUtil authUtil;

    private final EntityManager entityManager;


    @Override
    public List<DolbomLocation> getMyDolbomLocations() {
        return repository.findByMemberId(authUtil.getLoginMemberId());
    }

    @Override
    public DolbomLocation create(DolbomLocationCreateReq req) {
        Member loginMember = entityManager.find(Member.class, authUtil.getLoginMemberId());
        DolbomLocation newLocation = DolbomLocation.from(req, loginMember);

        repository.save(newLocation);

        return newLocation;
    }

    @Override
    public void delete(Long id) {
        DolbomLocation dolbomLocation = repository.findById(id).orElseThrow();
        if (!Objects.equals(dolbomLocation.getMember().getId(), authUtil.getLoginMemberId())) {
            throw new CustomException(ResponseCode.FORBIDDEN);
        }

        repository.delete(dolbomLocation);
    }
}
