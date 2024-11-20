package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    List<Certificate> findByTeacherProfileId(Long id);
}
