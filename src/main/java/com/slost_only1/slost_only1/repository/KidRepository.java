package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KidRepository extends JpaRepository<Kid, Long> {


    List<Kid> findByMemberId(Long memberId);

    long countByMemberId(Long id);
}
