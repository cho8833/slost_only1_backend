package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.TeacherProfile;
import com.slost_only1.slost_only1.repository.custom.TeacherProfileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, Long>, TeacherProfileRepositoryCustom {

    TeacherProfile findByMemberId(Long id);

}
