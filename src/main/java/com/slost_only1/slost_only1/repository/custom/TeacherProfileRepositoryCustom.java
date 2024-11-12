package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.model.TeacherProfile;

import java.util.List;

public interface TeacherProfileRepositoryCustom {
    List<TeacherProfile> findByDolbomId(Long id);
}
