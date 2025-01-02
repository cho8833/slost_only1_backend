package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.DolbomDow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DolbomDowRepository extends JpaRepository<DolbomDow, Long> {
    List<DolbomDow> findByDolbom_IdIn(Collection<Long> ids);

}
