package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.AvailableAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableAgeRepository extends JpaRepository<AvailableAge, Long> {
    long deleteByTeacherProfile_Id(Long id);
}
