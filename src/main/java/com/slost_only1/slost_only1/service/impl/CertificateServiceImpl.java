package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.req.CertificateCreateReq;
import com.slost_only1.slost_only1.model.Certificate;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.CertificateRepository;
import com.slost_only1.slost_only1.repository.CloudFileRepository;
import com.slost_only1.slost_only1.service.CertificateService;
import com.slost_only1.slost_only1.service.MyTeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository repository;
    private final AuthUtil authUtil;
    private final MyTeacherProfileService myTeacherProfileService;
    private final CloudFileRepository cloudFileRepository;

    @Override
    public List<Certificate> getMyCertificates() {
        TeacherProfile teacherProfile = myTeacherProfileService.getMyTeacherProfile();
        return repository.findByTeacherProfileId(teacherProfile.getId());
    }

    @Override
    public Certificate createCertificate(CertificateCreateReq req) throws IOException {
        TeacherProfile teacherProfile = myTeacherProfileService.getMyTeacherProfile();

        String fileUrl = null;
        // 파일 업로드는 선택사항
        if (req.pdf() != null) {
            // pdf 업로드
            fileUrl = cloudFileRepository.saveFile(req.pdf());
        }

        Certificate certificate = new Certificate(req.title(), fileUrl, teacherProfile);

        repository.save(certificate);

        return certificate;
    }

    @Override
    public Certificate editCertificate(Long id, CertificateCreateReq req) throws IOException {
        Certificate certificate = repository.findById(id).orElseThrow();

        // 자격증 수정 하지 않을 시
        if (req.pdf() != null) {
            // 이미 업로드되어 있는 pdf 삭제
            if (certificate.getFileUrl() != null) {
                List<String> split = Arrays.stream(certificate.getFileUrl()
                        .split("/")).toList();
                String fileName = split.get(split.size()-1);
                cloudFileRepository.deleteFile(fileName);
            }
            String newUrl = cloudFileRepository.saveFile(req.pdf());

            certificate.setFileUrl(newUrl);
        }

        certificate.setTitle(req.title());

        repository.save(certificate);

        return certificate;

    }
}
