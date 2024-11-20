package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.data.AreaReq;
import com.slost_only1.slost_only1.data.req.TeacherProfileEditReq;
import com.slost_only1.slost_only1.model.AvailableArea;
import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.AvailableAreaRepository;
import com.slost_only1.slost_only1.repository.DolbomAppliedTeacherRepository;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import com.slost_only1.slost_only1.util.AuthUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;

    private final DolbomAppliedTeacherRepository dolbomAppliedTeacherRepository;

    private final AvailableAreaRepository availableAreaRepository;

    private final EntityManager entityManager;

    private final AuthUtil authUtil;

    @Override
    public List<TeacherProfile> getDolbomPendingTeacher(Long dolbomId) {
        return teacherProfileRepository.findByDolbomId(dolbomId);
    }

    @Override
    public Page<TeacherProfile> getNearTeacher(AreaReq req, Pageable pageable) {
        return teacherProfileRepository.findBySigunguAndSido(req.sigungu(), req.sido(), pageable);
    }

    @Override
    public List<AvailableArea> getAvailableArea(Long teacherProfileId) {
        return availableAreaRepository.findByTeacherProfileId(teacherProfileId);
    }

    @Override
    public TeacherProfile getTeacherProfileById(Long id) {
        return teacherProfileRepository.findByMemberId(id).orElseThrow();
    }

    @Override
    public Page<TeacherProfile> getTeacherProfile(Pageable pageable) {
        return teacherProfileRepository.findAll(pageable);
    }

    @Override
    public TeacherProfile editTeacherProfile(Long id, TeacherProfileEditReq req) {
        TeacherProfile teacherProfile = teacherProfileRepository.findById(id).orElseThrow();

        if (req.howBecameTeacher() != null) {
            teacherProfile.setHowBecameTeacher(req.howBecameTeacher());
        }
        if (req.introduce() != null) {
            teacherProfile.setIntroduce(req.introduce());
        }

        teacherProfileRepository.save(teacherProfile);

        return teacherProfile;
    }
}
