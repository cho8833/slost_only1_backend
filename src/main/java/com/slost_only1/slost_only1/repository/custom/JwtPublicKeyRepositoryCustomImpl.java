package com.slost_only1.slost_only1.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.slost_only1.slost_only1.data.JwtPublicKeyRes;
import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.model.JwtPublicKey;
import com.slost_only1.slost_only1.model.QJwtPublicKey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JwtPublicKeyRepositoryCustomImpl implements JwtPublicKeyRepositoryCustom {
    private final RestTemplate restTemplate;

    private final JPAQueryFactory queryFactory;

    @Override
    public JwtPublicKeyRes fetchPublicKey(AuthProvider authProvider) {
        String url = authProvider.getPublicKeyUrl();

        ResponseEntity<JwtPublicKeyRes> response = restTemplate.exchange(
                url, HttpMethod.GET, null, JwtPublicKeyRes.class
        );

        return response.getBody();
    }

    @Override
    public List<JwtPublicKey> findByAuthProvider(AuthProvider authProvider) {
        QJwtPublicKey qJwtPublicKey = QJwtPublicKey.jwtPublicKey;

        return queryFactory.selectFrom(qJwtPublicKey)
                .where(qJwtPublicKey.authProvider.eq(authProvider))
                .fetch();
    }

    @Transactional
    @Override
    public long deleteByAuthProvider(AuthProvider authProvider) {
        QJwtPublicKey qJwtPublicKey = QJwtPublicKey.jwtPublicKey;

        return queryFactory.delete(qJwtPublicKey)
                .where(qJwtPublicKey.authProvider.eq(authProvider))
                .execute();
    }
}
