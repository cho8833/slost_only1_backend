package com.slost_only1.slost_only1.service;


import com.slost_only1.slost_only1.model.TeacherProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherProfileService {
    List<TeacherProfile> getPendingTeacher(Long dolbomId);

    Page<TeacherProfile> getNearTeacher(String bname, Pageable pageable);
}
