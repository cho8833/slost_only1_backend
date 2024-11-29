package com.slost_only1.slost_only1.service;


import com.slost_only1.slost_only1.data.req.AreaReq;
import com.slost_only1.slost_only1.data.req.TeacherProfileEditReq;
import com.slost_only1.slost_only1.model.AvailableArea;
import com.slost_only1.slost_only1.model.DolbomReview;
import com.slost_only1.slost_only1.model.TeacherProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherProfileService {
    List<TeacherProfile> getDolbomPendingTeacher(Long dolbomId);

    Page<TeacherProfile> getNearTeacher(AreaReq req, Pageable pageable);

    List<AvailableArea> getAvailableArea(Long teacherProfileId);

    TeacherProfile getTeacherProfileById(Long id);

    Page<TeacherProfile> getTeacherProfile(Pageable pageable);

    TeacherProfile editTeacherProfile(Long id, TeacherProfileEditReq req);

    TeacherProfile editTeacherProfileImage(Long id, MultipartFile file);

    Page<DolbomReview> getTeacherReview(Pageable pageable, Long teacherId, boolean includeReported);
}
