package com.slost_only1.slost_only1.data;


import com.slost_only1.slost_only1.enums.AuthProvider;
import com.slost_only1.slost_only1.model.JwtPublicKey;

import java.util.List;
import java.util.Optional;

public record JwtPublicKeyRes(List<PublicKey> keys) {

    public record PublicKey(
            String kty, String kid, String use, String alg, String n, String e
    ) {
    }

    public List<JwtPublicKey> parseKeys(AuthProvider authProvider) {
        return keys.stream().map(key ->
                new JwtPublicKey(key.kty, key.kid, key.alg, key.n, key.e, authProvider))
                .toList();
    }

    public Optional<PublicKey> findMatchingKey(String kid, String alg) {
        return this.keys.stream().filter(key -> key.kid.equals(kid) && key.alg.equals(alg))
                .findFirst();
    }
}
