package com.slost_only1.slost_only1.repository.custom;

import com.slost_only1.slost_only1.data.JwtPublicKeyRes;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.model.JwtPublicKey;
import jakarta.transaction.Transactional;

import java.util.List;

public interface JwtPublicKeyRepositoryCustom {
    JwtPublicKeyRes fetchPublicKey(AuthProvider authProvider);

    List<JwtPublicKey> findByAuthProvider(AuthProvider authProvider);

    @Transactional
    long deleteByAuthProvider(AuthProvider authProvider);
}
