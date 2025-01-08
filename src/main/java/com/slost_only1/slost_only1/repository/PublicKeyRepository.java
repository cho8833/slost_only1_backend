package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.model.JwtPublicKey;
import com.slost_only1.slost_only1.repository.custom.JwtPublicKeyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicKeyRepository extends JpaRepository<JwtPublicKey, Long>, JwtPublicKeyRepositoryCustom {

}
