package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.DolbomTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DolbomTimeSlotRepository extends JpaRepository<DolbomTimeSlot, Long> {
    List<DolbomTimeSlot> findByDolbom_IdIn(Collection<Long> ids);

}
