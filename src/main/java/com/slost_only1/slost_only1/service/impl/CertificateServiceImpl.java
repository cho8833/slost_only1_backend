package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.req.CertificateCreateReq;
import com.slost_only1.slost_only1.model.Certificate;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.repository.CertificateRepository;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.CertificateService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository repository;
    private final AuthUtil authUtil;
    private final TeacherProfileRepository teacherProfileRepository;
    private final EntityManager entityManager;

    @Override
    public List<Certificate> getMyCertificates() {
        return repository.findByMemberId(authUtil.getLoginMemberId());
    }

    @Override
    public Certificate createCertificate(CertificateCreateReq req) {
        Member member = entityManager.getReference(Member.class, authUtil.getLoginMemberId());
        Certificate certificate = Certificate.of(req, member);

        repository.save(certificate);

        return certificate;
    }
}
