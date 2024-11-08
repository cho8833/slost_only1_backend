package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.DolbomLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DolbomLocationRepository extends JpaRepository<DolbomLocation, Long> {

    List<DolbomLocation> findByMemberId(Long memberId);
}
