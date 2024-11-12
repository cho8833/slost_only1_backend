package com.slost_only1.slost_only1.service;


import com.slost_only1.slost_only1.model.TeacherProfile;

import java.util.List;

public interface TeacherProfileService {
    List<TeacherProfile> getPendingTeacher(Long dolbomId);
}
