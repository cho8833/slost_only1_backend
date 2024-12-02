package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.AvailableCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCategoryRepository extends JpaRepository<AvailableCategory, Long> {
    long deleteByTeacherProfile_Id(Long id);

}
