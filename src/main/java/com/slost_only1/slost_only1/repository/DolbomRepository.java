package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.Dolbom;
import com.slost_only1.slost_only1.repository.custom.DolbomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DolbomRepository extends JpaRepository<Dolbom, Long>, DolbomRepositoryCustom {

}
