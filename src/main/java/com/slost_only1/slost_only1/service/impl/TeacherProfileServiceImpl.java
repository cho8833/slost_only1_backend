package com.slost_only1.slost_only1.service.impl;

import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.TeacherDolbomRepository;
import com.slost_only1.slost_only1.repository.TeacherProfileRepository;
import com.slost_only1.slost_only1.service.TeacherProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherProfileServiceImpl implements TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;

    private final TeacherDolbomRepository teacherDolbomRepository;

    @Override
    public List<TeacherProfile> getPendingTeacher(Long dolbomId) {

        return teacherProfileRepository.findByDolbomId(dolbomId);
    }

    @Override
    public Page<TeacherProfile> getNearTeacher(String bname, Pageable pageable) {
        return teacherProfileRepository.findByBname(bname, pageable);
    }
}
