package com.slost_only1.slost_only1.repository;

import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.model.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    long deleteByMember_Id(Long id);

    Optional<OAuth> findByUserIdAndAuthProvider(String userId, AuthProvider authProvider);
}
