package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.TeacherProfileRes;
import com.slost_only1.slost_only1.data.req.TeacherProfileCreateReq;
import com.slost_only1.slost_only1.enums.TeacherProfileStatus;
import com.slost_only1.slost_only1.model.AvailableArea;
import com.slost_only1.slost_only1.model.Member;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.AvailableAreaRepository;
import com.slost_only1.slost_only1.repository.TeacherDolbomRepository;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;

    private final TeacherDolbomRepository teacherDolbomRepository;

    private final AvailableAreaRepository availableAreaRepository;

    private final EntityManager entityManager;

    private final AuthUtil authUtil;

    @Override
    public List<TeacherProfile> getDolbomPendingTeacher(Long dolbomId) {
        return teacherProfileRepository.findByDolbomId(dolbomId);
    }

    @Override
    public Page<TeacherProfile> getNearTeacher(String bname, Pageable pageable) {
        return teacherProfileRepository.findBySigungu(bname, pageable);
    }

    @Override
    public List<AvailableArea> getAvailableArea(Long teacherProfileId) {
        return availableAreaRepository.findByTeacherProfileId(teacherProfileId);
    }

    @Override
    @Transactional
    public TeacherProfileRes createProfile(TeacherProfileCreateReq req, MultipartFile profileImg) {
        Member member = entityManager.getReference(Member.class, authUtil.getLoginMemberId());

        TeacherProfile teacherProfile = new TeacherProfile(req.getName(),
                req.getGender(), req.getBirthday(), req.getProfileName(),
                null, TeacherProfileStatus.PENDING, member);

        // TODO: S3 업로드 이후 이미지url 저장 필요
        teacherProfile.setProfileImageUrl("https://s.pstatic.net/static/www/mobile/edit/20241101_1095/upload_17304407629933y6Lu.png");

        teacherProfileRepository.save(teacherProfile);

        List<AvailableArea> areas = req.getAvailableArea().stream()
                .map(area -> AvailableArea.from(area, teacherProfile)).toList();

        availableAreaRepository.saveAll(areas);

        return TeacherProfileRes.from(teacherProfile);
    }

    @Override
    public TeacherProfile getTeacherProfile(Long id) {
        return teacherProfileRepository.findById(id).orElseThrow();
    }
}
