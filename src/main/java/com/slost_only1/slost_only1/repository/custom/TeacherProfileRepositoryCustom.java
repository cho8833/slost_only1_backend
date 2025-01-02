package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.model.TeacherProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TeacherProfileRepositoryCustom {
    List<TeacherProfile> findByAppliedDolbomId(Long id);
    Map<Long, List<TeacherProfile>> findByAppliedDolbomIdsIn(List<Long> ids);
    TeacherProfile findByDolbomId(Long id);
    Page<TeacherProfile> findBySigunguAndSido(String sigungu, String sido, Pageable pageable);
}
