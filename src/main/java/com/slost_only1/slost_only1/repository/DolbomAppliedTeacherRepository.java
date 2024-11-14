package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.DolbomAppliedTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DolbomAppliedTeacherRepository extends JpaRepository<DolbomAppliedTeacher, Long> {
    Optional<DolbomAppliedTeacher> findByDolbomIdAndTeacherProfileId(Long dolbomId, Long teacherProfileId);
}
