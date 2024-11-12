package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.model.TeacherProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherProfileRepositoryCustom {
    List<TeacherProfile> findByDolbomId(Long id);

    Page<TeacherProfile> findBySigungu(String sigungu, Pageable pageable);
}
