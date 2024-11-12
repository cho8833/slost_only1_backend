package com.slost_only1.slost_only1.service;


import com.slost_only1.slost_only1.data.TeacherProfileRes;
import com.slost_only1.slost_only1.data.req.TeacherProfileCreateReq;
import com.slost_only1.slost_only1.model.AvailableArea;
import com.slost_only1.slost_only1.model.TeacherProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherProfileService {
    List<TeacherProfile> getDolbomPendingTeacher(Long dolbomId);

    Page<TeacherProfile> getNearTeacher(String sigungu, Pageable pageable);

    List<AvailableArea> getAvailableArea(Long teacherProfileId);

    TeacherProfileRes createProfile(TeacherProfileCreateReq req, MultipartFile profileImg);
}
