package com.slost_only1.slost_only1.repository;


import com.slost_only1.slost_only1.model.DolbomNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DolbomNoticeRepository extends JpaRepository<DolbomNotice, Long>, DolbomNoticeRepositoryCustom {
}
