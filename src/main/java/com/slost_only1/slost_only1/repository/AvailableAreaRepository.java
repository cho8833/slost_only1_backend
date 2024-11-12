package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.AvailableArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableAreaRepository extends JpaRepository<AvailableArea, Long> {

    List<AvailableArea> findByTeacherProfileId(Long id);
}
